package com.pitang.car.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pitang.car.entities.Car;
import com.pitang.car.repositories.CarRepository;
import com.pitang.common.dtos.cars.CarDTO;
import com.pitang.common.dtos.users.UserDTO;
import com.pitang.common.proxies.UserProxy;

/**
 * Classe de implementação dos testes do {@link CarService}.
 */
class CarServiceTest {

	@Mock
	private CarRepository carRepository;

	@Mock
	private UserProxy userProxy;

	@InjectMocks
	private CarService carService;

	Car car1 = new Car(1L, 2020, "ABC-1234", "ONIX", "PRETO");
	Car car2 = new Car(2L, 2021, "XYZ-5678", "YARIS", "BRANCO");
	
	CarDTO carDTO1 = new CarDTO(1L, 2020, "ABC-1234", "ONIX", "PRETO");
	CarDTO carDTO2 = new CarDTO(2L, 2021, "XYZ-5678", "YARIS", "BRANCO");

	private UserDTO userDTO = new UserDTO(1L, "João", "Silva", "joao.silva@gmail.com", new Date(), "joaosilva",
			"senha123", "1234567890", Arrays.asList(carDTO1, carDTO2));

	/**
	 * Inicializa os objetos necessarios antes de cada teste.
	 */
	@BeforeEach
	void runBeforeEachTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAllCarsByUserLogin_UserExists() {
		when(userProxy.findUserByLogin(userDTO.getLogin())).thenReturn(userDTO);
		when(carRepository.findAllById(Arrays.asList(1L, 2L)))
				.thenReturn(Arrays.asList(car1, car2));

		// Act
		List<CarDTO> result = carService.findAllCarsByUserLogin(userDTO.getLogin());

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(1L, result.get(0).getId());
		assertEquals(2020, result.get(0).getYear());
		assertEquals("ABC-1234", result.get(0).getLicensePlate());
		assertEquals("ONIX", result.get(0).getModel());
		assertEquals("PRETO", result.get(0).getColor());

		assertEquals(2L, result.get(1).getId());
		assertEquals(2021, result.get(1).getYear());
		assertEquals("XYZ-5678", result.get(1).getLicensePlate());
		assertEquals("YARIS", result.get(1).getModel());
		assertEquals("BRANCO", result.get(1).getColor());

		verify(userProxy).findUserByLogin(userDTO.getLogin());
		verify(carRepository).findAllById(Arrays.asList(1L, 2L));
	}

	@Test
	void testFindAllCarsByUserLogin_UserDoesNotExist() {
		String login = "user1";

		when(userProxy.findUserByLogin(login)).thenReturn(null);

		List<CarDTO> result = carService.findAllCarsByUserLogin(login);

		assertNotNull(result);
		assertTrue(result.isEmpty());

		verify(userProxy).findUserByLogin(login);
		verifyNoInteractions(carRepository);
	}
}
