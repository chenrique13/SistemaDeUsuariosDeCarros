package com.pitang.car.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pitang.car.entities.Car;
import com.pitang.car.services.CarService;
import com.pitang.common.dtos.cars.CarDTO;
import com.pitang.common.dtos.cars.SaveUpdateCarDTO;
import com.pitang.common.proxies.CarProxy;
import com.pitang.common.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/")
public class CarController implements CarProxy {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private CarService servicoCar;

	@Autowired
	private HttpServletRequest request;

	/**
	 * Endpoint responsavel por buscar todos os carros. {@link Car}
	 *
	 * @autor Carlos Pereira
	 *
	 * @return ResponseEntity<List<{@link CarDTO}>>
	 */
	@Override
	public ResponseEntity<List<CarDTO>> findAllCars() {
		return ResponseEntity.ok(servicoCar.findAllCarsByUserLogin(jwtUtils.getLoginFromToken(request)));
	}

	/**
	 * Endpoint responsavel por buscar um carro por id. {@link Car}
	 *
	 * @autor Carlos Pereira
	 *
	 * @param id
	 * @return ResponseEntity<<{@link CarDTO}>>
	 */
	@Override
	public ResponseEntity<CarDTO> findCarById(@PathVariable Long id) {
		return ResponseEntity.ok(servicoCar.findCarById(id, jwtUtils.getLoginFromToken(request)));
	}

	/**
	 * EndPoint responsavel por cadastrar um novo carro no sistema. {@link Car}
	 *
	 * @autor Carlos Pereira
	 *
	 * @param newcar
	 * @return ResponseEntity< {@link Car} >
	 */
	@Override
	public ResponseEntity<CarDTO> insertCar(@RequestBody SaveUpdateCarDTO newcar) {
		CarDTO novoCarro = servicoCar.insertCar(newcar, jwtUtils.getLoginFromToken(request));

		URI uri = UriComponentsBuilder.fromPath("car/").buildAndExpand(novoCarro.getId()).toUri();
		return ResponseEntity.created(uri).body(novoCarro);
	}

	/**
	 * EndPoint responsavel por atualizar um carro do sistema. {@link Car}
	 *
	 * @autor Carlos Pereira
	 *
	 * @param id
	 * @param updateCarDTO
	 * @return ResponseEntity< {@link Car} >
	 */
	@Override
	public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @RequestBody SaveUpdateCarDTO updateCarDTO) {
		CarDTO carroAtualizado = servicoCar.updateCar(id, updateCarDTO, jwtUtils.getLoginFromToken(request));

		if (carroAtualizado != null) {
			return ResponseEntity.ok(carroAtualizado);
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * EndPoint responsavel por excluir um carro do sistema. {@link Car}
	 *
	 * @autor Carlos Pereira
	 *
	 * @param id
	 * @return ResponseEntity< {@link Void} >
	 */
	@Override
	public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
		servicoCar.deleteCar(id, jwtUtils.getLoginFromToken(request));

		return ResponseEntity.noContent().build();
	}

	/**
	 * EndPoint interno responsavel por buscar uma lista de carro do sistema.
	 * {@link Car}
	 *
	 * @autor Carlos Pereira
	 *
	 * @param id
	 * @return ResponseEntity< {@link Void} >
	 */
	@Override
	public List<CarDTO> findAllCarsbyIds(List<Long> listIdsCars) {
		return servicoCar.findAllCarsByIds(listIdsCars);
	}

}
