package com.pitang.car.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pitang.car.entities.Car;
import com.pitang.car.repositories.CarRepository;
import com.pitang.common.dtos.cars.CarDTO;
import com.pitang.common.dtos.cars.SaveUpdateCarDTO;
import com.pitang.common.dtos.users.UserDTO;
import com.pitang.common.exceptions.CustomException;
import com.pitang.common.proxies.UserProxy;

/**
 * Classe responsável por fornecer operações de negócio para a entidade
 * {@link Car}.
 */
@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private UserProxy userProxy;

	/**
	 * O método obtem todos os carros cadastrados do usuario no banco de dados a
	 * partir no login do usuario, se existir. {@link Car}
	 *
	 * @autor Carlos Pereira
	 * 
	 * @param login
	 * @return {@link CarDTO}
	 */
	public List<CarDTO> findAllCarsByUserLogin(String login) {
		List<CarDTO> listCarsDto = new ArrayList<CarDTO>();
		UserDTO userDTO = userProxy.findUserByLogin(login);

		if (userDTO != null) {
			listCarsDto.addAll(carRepository.findAllById(userDTO.getCars()
					.stream()
					.map(CarDTO::getId)
					.collect(Collectors.toList()))
					.stream()
					.map(this::convertCarToCarDTO)
					.collect(Collectors.toList()));
		}
		
		return listCarsDto;
	}

	/**
	 * O método obtem todos os carros cadastrados do usuario no banco de dados a
	 * partir no login do usuario, se existir. {@link Car}
	 *
	 * @autor Carlos Pereira
	 * 
	 * @param listIdsCars
	 * @return {@link CarDTO}
	 */
	public List<CarDTO> findAllCarsByIds(List<Long> listIdsCars) {
		List<CarDTO> listCarsDto = new ArrayList<CarDTO>();
		List<Car> cars = carRepository.findAllById(listIdsCars);

		for (Car car : cars) {
			listCarsDto.add(convertCarToCarDTO(car));
		}

		return listCarsDto;
	}

	/**
	 * O método obtem um carro cadastrado do usuario no banco de dados a
	 * partir no login do usuario, se existir. {@link Car}
	 *
	 * @autor Carlos Pereira
	 *
	 * @param id
	 * @param login
	 * @return {@link CarDTO}
	 */
	public CarDTO findCarById(Long id, String login) {
		Optional<Car> car = carRepository.findById(id);

		if (car.isPresent()) {
			UserDTO user = userProxy.findUserByLogin(login);

			for (CarDTO carDTO : user.getCars()) {
				if (carDTO.getId().equals(car.get().getId())) {
					return convertCarToCarDTO(car.get());
				}
			}
		}
		return null;
	}

	/**
	 * Método usado para inserir um carro do usuario no banco de dados a partir no
	 * login do usuario. {@link Car}
	 *
	 * @autor Carlos Pereira
	 *
	 * @param newCar
	 * @param login
	 * @return {@link CarDTO}
	 */
	@Transactional
	public CarDTO insertCar(SaveUpdateCarDTO newCar, String login) {
		Car car = new Car(null, newCar.getYear(), newCar.getLicensePlate(), newCar.getModel(), newCar.getColor());
		validateInsertUpdateCar(car);

		Car carSaved = carRepository.save(car);

		if (login != null) {
			userProxy.addCarToUser(carSaved.getId(), login);
		}

		return convertCarToCarDTO(carSaved);
	}

	/**
	 * Metodo usado para atualizar um carro do usuario a partir do login, se
	 * existir. {@link Car}
	 *
	 * @autor Carlos Pereira
	 *
	 * @param id
	 * @param updateCar
	 * @param login
	 * @return {@link CarDTO}
	 */
	public CarDTO updateCar(Long id, SaveUpdateCarDTO updateCar, String login) {
		Optional<Car> car = carRepository.findById(id);

		if (car.isPresent()) {
			UserDTO user = userProxy.findUserByLogin(login);

			for (CarDTO carDTO : user.getCars()) {
				if (carDTO.getId().equals(car.get().getId())) {
					car.get().setYear(updateCar.getYear());
					car.get().setModel(updateCar.getModel());
					car.get().setLicensePlate(updateCar.getLicensePlate());
					car.get().setColor(updateCar.getColor());
					
					validateInsertUpdateCar(car.get());
					
					return convertCarToCarDTO(carRepository.save(car.get()));					
				}
			}
		}
		throw new CustomException("Invalid fields", 4, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Método usado para excluir por id um carro do usuario no banco de dados a
	 * partir no login do usuario, se existir. {@link Car}
	 *
	 * @autor Carlos Pereira
	 *
	 * @param id
	 * @param login
	 */
	public void deleteCar(Long id, String login) {
		Optional<Car> car = carRepository.findById(id);

		if (car.isPresent()) {
			if (login != null) {
				UserDTO user = userProxy.findUserByLogin(login);

				for (CarDTO carDTO : user.getCars()) {
					if (carDTO.getId().equals(car.get().getId())) {
						carRepository.delete(car.get());
						userProxy.deleteCarToUser(car.get().getId(), login);
					}
				}
			} else {
				carRepository.delete(car.get());
			}
		}
	}

	/**
	 * Método usado para converter um {@link Car} em um {@link CarDTO}.
	 * 
	 * @param car
	 * @return {@link CarDTO}
	 */
	private CarDTO convertCarToCarDTO(Car car) {
		if (car != null) {
			CarDTO carDTO = new CarDTO(car.getId(), car.getYear(), car.getLicensePlate(), car.getModel(),
					car.getColor());
			return carDTO;
		}
		return null;
	}

	/**
	 * Método usado para validar inserts e updates do carro.
	 * 
	 * @param car
	 */
	private void validateInsertUpdateCar(Car car) {
		if (!car.isValid()) {
			throw new CustomException("Missing fields", 5, HttpStatus.BAD_REQUEST);
		}

		Optional<Car> carLicensePlate = carRepository.findByLicensePlate(car.getLicensePlate());
		if (carLicensePlate.isPresent() && !carLicensePlate.get().equals(car)) {
			throw new CustomException("License plate already exists", 3, HttpStatus.CONFLICT);
		}
	}

}
