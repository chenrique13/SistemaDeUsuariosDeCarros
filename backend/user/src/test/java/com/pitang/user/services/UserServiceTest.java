package com.pitang.user.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

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
 * Classe de implementação dos testes do {@link UserService}.
 */
public class UserServiceTest {
	@Mock
	private UserRepository userRepository;

	@Mock
	private CarProxy carProxy;

	@InjectMocks
	private UserService userService;

	private List<User> mockUsers;

	@Mock
	private PasswordEncoder passwordEncoder; // Adicione o mock do PasswordEncoder

	private List<Long> car1 = Arrays.asList(1L, 2L);
	private List<Long> car2 = Arrays.asList(3L);

	private User user1 = new User(1L, "João", "Silva", "joao.silva@gmail.com", new Date(), "joaosilva", "senha123",
			"1234567890", car1);
	private User user2 = new User(2L, "Maria", "Oliveira", "maria.oliveira@gmail.com", new Date(), "mariaoliveira",
			"senha123", "0987654321", car2);

	/**
	 * Inicializa os objetos necessarios antes de cada teste.
	 */
	@BeforeEach
	void runBeforeEachTest() {
		MockitoAnnotations.openMocks(this);

		user1.setCreatedAt(LocalDateTime.of(2023, 2, 1, 0, 0));
		user1.setLastLogin(LocalDateTime.of(2024, 2, 1, 0, 0));

		mockUsers = new ArrayList<>();
		mockUsers.add(user1);
		mockUsers.add(user2);

		when(userRepository.findAll()).thenReturn(mockUsers);
		when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
		when(userRepository.findByLogin(user1.getLogin())).thenReturn(Optional.of(user1));

		when(carProxy.findAllCarsbyIds(car1))
				.thenReturn(Arrays.asList(new CarDTO(1L, 2020, "ABC-1234", "ONIX", "PRETO"),
						new CarDTO(2L, 2021, "XYZ-5678", "YARIS", "BRANCO")));

		when(carProxy.findAllCarsbyIds(car2))
				.thenReturn(Arrays.asList(new CarDTO(3L, 2022, "YUI-9101", "KICKS", "PRATA")));
	}

	@Test
	void testFindAllUsers() {
		List<UserDTO> result = userService.findAllUsers();

		assertNotNull(result);
		assertEquals(2, result.size());

		UserDTO userDTO1 = result.get(0);
		assertEquals(user1.getId(), userDTO1.getId());
		assertEquals(user1.getFirstName(), userDTO1.getFirstName());
		assertEquals(user1.getLastName(), userDTO1.getLastName());
		assertEquals(user1.getEmail(), userDTO1.getEmail());
		assertEquals(user1.getLogin(), userDTO1.getLogin());
		assertEquals(user1.getPassword(), userDTO1.getPassword());
		assertEquals(user1.getPhone(), userDTO1.getPhone());
		assertEquals(user1.getCars().size(), userDTO1.getCars().size());

		assertEquals(1L, userDTO1.getCars().get(0).getId());
		assertEquals(2020, userDTO1.getCars().get(0).getYear());
		assertEquals("ABC-1234", userDTO1.getCars().get(0).getLicensePlate());
		assertEquals("ONIX", userDTO1.getCars().get(0).getModel());
		assertEquals("PRETO", userDTO1.getCars().get(0).getColor());

		assertEquals(2L, userDTO1.getCars().get(1).getId());
		assertEquals(2021, userDTO1.getCars().get(1).getYear());
		assertEquals("XYZ-5678", userDTO1.getCars().get(1).getLicensePlate());
		assertEquals("YARIS", userDTO1.getCars().get(1).getModel());
		assertEquals("BRANCO", userDTO1.getCars().get(1).getColor());

		UserDTO userDTO2 = result.get(1);
		assertEquals(user2.getId(), userDTO2.getId());
		assertEquals(user2.getFirstName(), userDTO2.getFirstName());
		assertEquals(user2.getLastName(), userDTO2.getLastName());
		assertEquals(user2.getEmail(), userDTO2.getEmail());
		assertEquals(user2.getLogin(), userDTO2.getLogin());
		assertEquals(user2.getPassword(), userDTO2.getPassword());
		assertEquals(user2.getPhone(), userDTO2.getPhone());
		assertEquals(user2.getCars().size(), userDTO2.getCars().size());

		assertEquals(3L, userDTO2.getCars().get(0).getId());
		assertEquals(2022, userDTO2.getCars().get(0).getYear());
		assertEquals("YUI-9101", userDTO2.getCars().get(0).getLicensePlate());
		assertEquals("KICKS", userDTO2.getCars().get(0).getModel());
		assertEquals("PRATA", userDTO2.getCars().get(0).getColor());
	}

	@Test
	void testFindAllUsers_EmptyList() {
		when(userRepository.findAll()).thenReturn(Collections.emptyList());

		List<UserDTO> result = userService.findAllUsers();

		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	void testFindUserById_UserExists() {
		UserDTO result = userService.findUserById(user1.getId());

		assertNotNull(result);
		assertEquals(user1.getId(), result.getId());
		assertEquals(user1.getFirstName(), result.getFirstName());
		assertEquals(user1.getLastName(), result.getLastName());
		assertEquals(user1.getEmail(), result.getEmail());
		assertEquals(user1.getLogin(), result.getLogin());
		assertEquals(user1.getPassword(), result.getPassword());
		assertEquals(user1.getPhone(), result.getPhone());
		assertEquals(user1.getCars().size(), result.getCars().size());

		verify(userRepository).findById(user1.getId());
	}

	@Test
	void testFindUserById_UserDoesNotExist() {
		when(userRepository.findById(user1.getId())).thenReturn(Optional.empty());

		UserDTO result = userService.findUserById(user1.getId());

		assertNull(result);

		verify(userRepository).findById(user1.getId());
	}

	@Test
	void testFindUserByLogin_UserExists() {
		UserDTO result = userService.findUserByLogin(user1.getLogin());

		assertNotNull(result);
		assertEquals(user1.getId(), result.getId());
		assertEquals(user1.getFirstName(), result.getFirstName());
		assertEquals(user1.getLastName(), result.getLastName());
		assertEquals(user1.getEmail(), result.getEmail());
		assertEquals(user1.getLogin(), result.getLogin());
		assertEquals(user1.getPassword(), result.getPassword());
		assertEquals(user1.getPhone(), result.getPhone());
		assertEquals(user1.getCars().size(), result.getCars().size());

		verify(userRepository).findByLogin(user1.getLogin());
	}

	@Test
	void testFindUserByLogin_UserDoesNotExist() {
		when(userRepository.findByLogin(user1.getLogin())).thenReturn(Optional.empty());

		UserDTO result = userService.findUserByLogin(user1.getLogin());

		assertNull(result);

		verify(userRepository).findByLogin(user1.getLogin());
	}

	@Test
	void testFindMe_UserExists() {
		UserInfoDTO result = userService.findMe(user1.getLogin());

		assertNotNull(result);
		assertEquals(user1.getFirstName(), result.getFirstName());
		assertEquals(user1.getLastName(), result.getLastName());
		assertEquals(user1.getEmail(), result.getEmail());
		assertEquals(user1.getLogin(), result.getLogin());
		assertEquals(user1.getPhone(), result.getPhone());
		assertEquals(user1.getCars().size(), result.getCars().size());
		assertEquals(LocalDateTime.of(2023, 2, 1, 0, 0), result.getCreatedAt());
		assertEquals(LocalDateTime.of(2024, 2, 1, 0, 0), result.getLastLogin());

		verify(userRepository).findByLogin(user1.getLogin());
		verify(carProxy).findAllCarsbyIds(car1);
	}

	@Test
	void testFindMe_UserDoesNotExist() {
		when(userRepository.findByLogin(user1.getLogin())).thenReturn(Optional.empty());

		UserInfoDTO result = userService.findMe(user1.getLogin());

		assertNull(result);

		verify(userRepository).findByLogin(user1.getLogin());
		verify(carProxy, never()).findAllCarsbyIds(anyList());
	}

	@Test
	void testInsertUser() {
		SaveUpdateCarDTO carDTO = new SaveUpdateCarDTO(2020, "ABC-1234", "ONIX", "PRETO");
		SaveUserDTO saveUserDTO = new SaveUserDTO("João", "Silva", "joao.silva@gmail.com", new Date(), "joaosilva2",
				"senha123", "1234567890", Collections.singletonList(carDTO));

		User mockUser = new User(saveUserDTO.getFirstName(), saveUserDTO.getLastName(), saveUserDTO.getEmail(),
				saveUserDTO.getBirthday(), saveUserDTO.getLogin(), "encodedPassword", saveUserDTO.getPhone(),
				Arrays.asList(1L));
		mockUser.setId(1L);

		when(carProxy.insertCar(any(SaveUpdateCarDTO.class)))
				.thenReturn(ResponseEntity.ok(new CarDTO(1L, 2020, "ABC-1234", "ONIX", "PRETO")));
		when(userRepository.save(any(User.class))).thenReturn(mockUser);
		when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("senha123");

		UserDTO result = userService.insertUser(saveUserDTO);

		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("João", result.getFirstName());
		assertEquals("Silva", result.getLastName());
		assertEquals("joao.silva@gmail.com", result.getEmail());
		assertEquals("joaosilva2", result.getLogin());
		assertEquals("1234567890", result.getPhone());

		verify(userRepository).save(any(User.class));
		verify(carProxy).insertCar(any(SaveUpdateCarDTO.class));
		verify(passwordEncoder).encode(any(CharSequence.class));
		verify(userRepository).findByLogin(anyString());
	}

	@Test
	void testUpdateUser_Success() {
		UpdateUserDTO updateUserDTO = new UpdateUserDTO("Carlos", "Oliveira", "carlos.oliveira@gmail.com", new Date(),
				"carlosoliveira", "novaSenha123", "9876543210");

		User existingUser = new User(1L, "João", "Silva", "joao.silva@gmail.com", new Date(), "joaosilva", "senha123",
				"1234567890", car1);

		User updatedUser = new User(1L, "Carlos", "Oliveira", "carlos.oliveira@gmail.com", new Date(), "carlosoliveira",
				"novaSenha123", "9876543210", car1);

		when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
		when(passwordEncoder.encode(updateUserDTO.getPassword())).thenReturn("novaSenha123");
		when(userRepository.save(any(User.class))).thenReturn(updatedUser);

		UserDTO result = userService.updateUser(1L, updateUserDTO);

		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("Carlos", result.getFirstName());
		assertEquals("Oliveira", result.getLastName());
		assertEquals("carlos.oliveira@gmail.com", result.getEmail());
		assertEquals("carlosoliveira", result.getLogin());
		assertEquals("9876543210", result.getPhone());
		assertEquals("novaSenha123", result.getPassword());

		verify(userRepository).findById(1L);
		verify(userRepository).save(any(User.class));
		verify(passwordEncoder).encode(updateUserDTO.getPassword());
	}

	@Test
	void testUpdateUser_UserNotFound() {
		UpdateUserDTO updateUserDTO = new UpdateUserDTO("Carlos", "Oliveira", "carlos.oliveira@gmail.com", new Date(),
				"carlosoliveira", "novaSenha123", "9876543210");

		when(userRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(CustomException.class, () -> userService.updateUser(1L, updateUserDTO));
	}

	@Test
	void testDeleteUser_UserExists() {
		when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
		when(carProxy.deleteCar(any(Long.class))).thenReturn(null);
		doNothing().when(userRepository).delete(any(User.class));

		userService.deleteUser(user1.getId());

		verify(userRepository).findById(user1.getId());
		verify(carProxy, times(user1.getCars().size())).deleteCar(any(Long.class));
		verify(userRepository).delete(user1);
	}

	@Test
	void testDeleteUser_UserDoesNotExist() {
		when(userRepository.findById(user1.getId())).thenReturn(Optional.empty());

		userService.deleteUser(user1.getId());

		verify(userRepository).findById(user1.getId());
		verify(carProxy, never()).deleteCar(any(Long.class));
		verify(userRepository, never()).delete(any(User.class));
	}

	@Test
	void testUpdateLastLoginUser_UserExists() {
		LocalDateTime currentDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

		when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
		when(userRepository.save(any(User.class))).thenReturn(user1);

		userService.updateLastLoginUser(user1.getId());

		verify(userRepository).findById(user1.getId());
		verify(userRepository).save(user1);

		assertNotNull(user1.getLastLogin());
		assertEquals(currentDateTime, user1.getLastLogin().truncatedTo(ChronoUnit.SECONDS));
		assertNotNull(user1.getCurrentLogin());
		assertEquals(currentDateTime, user1.getCurrentLogin().truncatedTo(ChronoUnit.SECONDS));
	}

	@Test
	void testUpdateLastLoginUser_UserDoesNotExist() {
		Long userId = 10L;

		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		userService.updateLastLoginUser(userId);

		verify(userRepository).findById(userId);
		verify(userRepository, never()).save(any(User.class));
	}

	@Test
	void testAddCarToUser_UserExists() {
		when(userRepository.findByLogin(user1.getLogin())).thenReturn(Optional.of(user1));
		when(userRepository.save(any(User.class))).thenReturn(user1);

		userService.addCarToUser(car1.get(0), user1.getLogin());

		verify(userRepository).findByLogin(user1.getLogin());
		verify(userRepository).save(user1);

		assertTrue(user1.getCars().contains(car1.get(0)));
	}

	@Test
	void testAddCarToUser_UserDoesNotExist() {
		when(userRepository.findByLogin(user1.getLogin())).thenReturn(Optional.empty());

		userService.addCarToUser(1L, user1.getLogin());

		verify(userRepository).findByLogin(user1.getLogin());
		verify(userRepository, never()).save(any(User.class));
	}
	
	@Test
	void testDeleteCarToUser_UserExists() {
		when(userRepository.findByLogin(user1.getLogin())).thenReturn(Optional.of(user1));
		when(userRepository.save(any(User.class))).thenReturn(user1);

		userService.deleteCarToUser(car1.get(0), user1.getLogin());

		verify(userRepository).findByLogin(user1.getLogin());
		verify(userRepository).save(user1);

		assertFalse(user1.getCars().contains(car1.get(0)));
	}

	@Test
	void testDeleteCarToUser_UserDoesNotExist() {
		when(userRepository.findByLogin(user1.getLogin())).thenReturn(Optional.empty());

		userService.deleteCarToUser(1L, user1.getLogin());

		verify(userRepository).findByLogin(user1.getLogin());
		verify(userRepository, never()).save(any(User.class));
	}

}