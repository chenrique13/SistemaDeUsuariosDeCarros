package com.pitang.user.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pitang.common.dtos.cars.CarDTO;
import com.pitang.common.dtos.cars.SaveUpdateCarDTO;
import com.pitang.common.dtos.users.SaveUserDTO;
import com.pitang.common.dtos.users.UpdateUserDTO;
import com.pitang.common.dtos.users.UserDTO;
import com.pitang.common.dtos.users.UserInfoDTO;
import com.pitang.common.exceptions.CustomException;
import com.pitang.common.proxies.CarProxy;
import com.pitang.user.entities.User;
import com.pitang.user.repositories.UserRepository;

/**
 * Classe responsável por fornecer operações de negócio para a entidade
 * {@link User}.
 */
@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarProxy carProxy;

	/**
	 * O método obtem todos os {@link User} cadastratos no banco de dados, se
	 * existir.
	 *
	 * @autor Carlos Pereira
	 *
	 * @return {@link UserDTO}
	 */
	public List<UserDTO> findAllUsers() {
		List<User> listUsers = userRepository.findAll();
		List<UserDTO> listUsersDTO = new ArrayList<UserDTO>();

		for (User user : listUsers) {
			listUsersDTO.add(convertUserToUserDTO(user));
		}

		return listUsersDTO;
	}

	/**
	 * O método obtem um {@link User} por id cadastrato no banco de dados, se
	 * existir.
	 *
	 * @autor Carlos Pereira
	 *
	 * @param id
	 * @return {@link UserDTO}
	 */
	public UserDTO findUserById(Long id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			return convertUserToUserDTO(user.get());
		}

		return null;
	}

	/**
	 * O método obtem um {@link User} por login cadastrato no banco de dados.
	 *
	 * @autor Carlos Pereira
	 *
	 * @param id
	 * @return {@link UserDTO}
	 */
	public UserDTO findUserByLogin(String login) {
		Optional<User> user = userRepository.findByLogin(login);

		if (user.isPresent()) {
			return convertUserToUserDTO(user.get());
		}

		return null;
	}

	/**
	 * O método obtem um {@link User} por login cadastrato no banco de dados.
	 *
	 * @autor Carlos Pereira
	 *
	 * @param login
	 * @return {@link UserInfoDTO}
	 */
	public UserInfoDTO findMe(String login) {
		Optional<User> user = userRepository.findByLogin(login);

		if (user.isPresent()) {
			List<CarDTO> listCarDTO = carProxy.findAllCarsbyIds(user.get().getCars());

			UserInfoDTO userInfoDTO = new UserInfoDTO(user.get().getFirstName(), user.get().getLastName(),
					user.get().getEmail(), user.get().getBirthday(), user.get().getLogin(), user.get().getPhone(),
					listCarDTO, user.get().getCreatedAt(), user.get().getLastLogin());

			return userInfoDTO;
		}
		return null;
	}

	/**
	 * Método usado para inserir um {@link User} no banco de dados.
	 *
	 * @autor Carlos Pereira
	 *
	 * @param saveUserDTO
	 * @return {@link UserDTO}
	 */
	@Transactional
	public UserDTO insertUser(SaveUserDTO saveUserDTO) {
		List<Long> idsCars = new ArrayList<Long>();

		User user = new User(saveUserDTO.getFirstName(), saveUserDTO.getLastName(), saveUserDTO.getEmail(),
				saveUserDTO.getBirthday(), saveUserDTO.getLogin(), passwordEncoder.encode(saveUserDTO.getPassword()),
				saveUserDTO.getPhone(), idsCars);

		validateInsertUpdateUser(user);

		for (SaveUpdateCarDTO saveUpdateCarDTO : saveUserDTO.getCars()) {
			SaveUpdateCarDTO newCar = new SaveUpdateCarDTO(saveUpdateCarDTO.getYear(), saveUpdateCarDTO.getLicensePlate(),
					saveUpdateCarDTO.getModel(), saveUpdateCarDTO.getColor());
			idsCars.add(carProxy.insertCar(newCar).getBody().getId());
		}

		return convertUserToUserDTO(userRepository.save(user));
	}

	/**
	 * Metodo usado para atualizar um {@link User}, se existir.
	 *
	 * @autor Carlos Pereira
	 *
	 * @param idPedido
	 * @param usuario
	 * @return {@link UserDTO}
	 */
	public UserDTO updateUser(Long id, UpdateUserDTO updateUserDTO) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			user.get().setFirstName(updateUserDTO.getFirstName());
			user.get().setLastName(updateUserDTO.getLastName());
			user.get().setEmail(updateUserDTO.getEmail());
			user.get().setBirthday(updateUserDTO.getBirthday());
			user.get().setLogin(updateUserDTO.getLogin());
			user.get().setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));
			user.get().setPhone(updateUserDTO.getPhone());

			validateInsertUpdateUser(user.get());

			return convertUserToUserDTO(userRepository.save(user.get()));
		}

		throw new CustomException("Invalid fields", 4, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Método usado para excluir por id um {@link User} no banco de dados, se
	 * existir.
	 *
	 * @autor Carlos Pereira
	 *
	 * @param id
	 */
	@Transactional
	public void deleteUser(Long id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			for (Long idCar : user.get().getCars()) {
				carProxy.deleteCar(idCar);
			}

			userRepository.delete(user.get());
		}
	}

	/**
	 * Método usado para atualizar as datas que o usuario realizou o login no banco
	 * de dados, se existir.
	 *
	 * @autor Carlos Pereira
	 *
	 * @param id
	 */
	public void updateLastLoginUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			LocalDateTime localDateTimenow = LocalDateTime.now();

			user.get().setLastLogin(
					user.get().getCurrentLogin() != null ? user.get().getCurrentLogin() : localDateTimenow);
			user.get().setCurrentLogin(localDateTimenow);

			userRepository.save(user.get());
		}
	}

	/**
	 * Método para adicionar um carro no usuario.
	 * 
	 * @autor Carlos Pereira
	 * 
	 * @param id
	 * @param login
	 */
	public void addCarToUser(Long id, String login) {
		Optional<User> user = userRepository.findByLogin(login);

		if (user.isPresent()) {
			user.get().getCars().add(id);
			userRepository.save(user.get());
		}
	}

	/**
	 * Método para deletar um carro no usuario.
	 * 
	 * @autor Carlos Pereira
	 * 
	 * @param id
	 * @param login
	 */
	public void deleteCarToUser(Long id, String login) {
		Optional<User> user = userRepository.findByLogin(login);

		if (user.isPresent()) {
			user.get().getCars().remove(id);
			userRepository.save(user.get());
		}
	}

	/**
	 * Método usado para converter um {@link User} em um {@link UserDTO}.
	 * 
	 * @autor Carlos Pereira
	 * 
	 * @param user
	 * @return {@link UserDTO}
	 */
	private UserDTO convertUserToUserDTO(User user) {
		List<CarDTO> listCarDTO = new ArrayList<CarDTO>();

		if (user.getCars() != null && !user.getCars().isEmpty()) {
			listCarDTO = carProxy.findAllCarsbyIds(user.getCars());
		}

		UserDTO userDTO = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getBirthday(), user.getLogin(), user.getPassword(), user.getPhone(), listCarDTO);

		return userDTO;
	}

	/**
	 * Método usado para validar inserts e updates do usuario.
	 * 
	 * @param user
	 */
	private void validateInsertUpdateUser(User user) {
		if (!user.isValid()) {
			throw new CustomException("Missing fields", 5, HttpStatus.BAD_REQUEST);
		}

		Optional<User> userEmail = userRepository.findByEmail(user.getEmail());
		if (userEmail.isPresent() && !userEmail.get().equals(user)) {
			throw new CustomException("Email already exists", 2, HttpStatus.CONFLICT);
		}

		Optional<User> userLogin = userRepository.findByLogin(user.getLogin());
		if (userLogin.isPresent() && !userLogin.get().equals(user)) {
			throw new CustomException("Login already exists", 3, HttpStatus.CONFLICT);
		}
	}

}
