package com.pitang.user.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.pitang.common.dtos.cars.CarDTO;
import com.pitang.common.dtos.users.UserDTO;
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

	/**
	 * Inicializa os objetos necessarios antes de cada teste.
	 */
	@BeforeEach
	void runBeforeEachTest() {
		MockitoAnnotations.openMocks(this);

		List<Long> carros1 = Arrays.asList(1L, 2L);
		List<Long> carros2 = Arrays.asList(3L);

		User user1 = new User(1L, "João", "Silva", "joao.silva@gmail.com", new Date(), "joaosilva", "senha123",
				"1234567890", carros1);
		User user2 = new User(2L, "Maria", "Oliveira", "maria.oliveira@gmail.com", new Date(), "mariaoliveira",
				"senha123", "0987654321", carros2);

		mockUsers = new ArrayList<>();
		mockUsers.add(user1);
		mockUsers.add(user2);

		when(userRepository.findAll()).thenReturn(mockUsers);
		when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
		when(userRepository.findByLogin(user1.getLogin())).thenReturn(Optional.of(user1));

		when(carProxy.findAllCarsbyIds(Arrays.asList(1L, 2L)))
				.thenReturn(Arrays.asList(new CarDTO(1L, 2020, "ABC-1234", "ONIX", "PRETO"),
						new CarDTO(2L, 2021, "XYZ-5678", "YARIS", "BRANCO")));

		when(carProxy.findAllCarsbyIds(Arrays.asList(3L)))
				.thenReturn(Arrays.asList(new CarDTO(3L, 2022, "YUI-9101", "KICKS", "PRATA")));
	}

	@Test
	void testFindAllUsers() {
		List<UserDTO> result = userService.findAllUsers();

		assertNotNull(result);
		assertEquals(2, result.size());

		UserDTO userDTO1 = result.get(0);
		assertEquals(1L, userDTO1.getId());
		assertEquals("João", userDTO1.getFirstName());
		assertEquals("Silva", userDTO1.getLastName());
		assertEquals("joao.silva@gmail.com", userDTO1.getEmail());
		assertEquals("joaosilva", userDTO1.getLogin());
		assertEquals("1234567890", userDTO1.getPhone());
		assertEquals(2, userDTO1.getCars().size());

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
		assertEquals(2L, userDTO2.getId());
		assertEquals("Maria", userDTO2.getFirstName());
		assertEquals("Oliveira", userDTO2.getLastName());
		assertEquals("maria.oliveira@gmail.com", userDTO2.getEmail());
		assertEquals("mariaoliveira", userDTO2.getLogin());
		assertEquals("0987654321", userDTO2.getPhone());
		assertEquals(1, userDTO2.getCars().size());

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
		Long userId = 1L;
		UserDTO result = userService.findUserById(userId);

		assertNotNull(result);
		assertEquals(userId, result.getId());
		assertEquals("João", result.getFirstName());
		assertEquals("Silva", result.getLastName());
		assertEquals("joao.silva@gmail.com", result.getEmail());
		assertEquals("joaosilva", result.getLogin());
		assertEquals("1234567890", result.getPhone());
		assertEquals(2, result.getCars().size());

		verify(userRepository).findById(userId);
	}

	@Test
	void testFindUserById_UserDoesNotExist() {
		Long userId = 1L;
		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		UserDTO result = userService.findUserById(userId);

		assertNull(result);

		verify(userRepository).findById(userId);
	}
	
	@Test
	void testFindUserByLogin_UserExists() {
		String userLogin = "joaosilva";
		UserDTO result = userService.findUserByLogin(userLogin);

		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("João", result.getFirstName());
		assertEquals("Silva", result.getLastName());
		assertEquals("joao.silva@gmail.com", result.getEmail());
		assertEquals(userLogin, result.getLogin());
		assertEquals("1234567890", result.getPhone());
		assertEquals(2, result.getCars().size());

		verify(userRepository).findByLogin(userLogin);
	}
	
	@Test
	void testFindUserByLogin_UserDoesNotExist() {
		String userLogin = "joaosilva";
		when(userRepository.findByLogin(userLogin)).thenReturn(Optional.empty());

		UserDTO result = userService.findUserByLogin(userLogin);

		assertNull(result);

		verify(userRepository).findByLogin(userLogin);
	}
}