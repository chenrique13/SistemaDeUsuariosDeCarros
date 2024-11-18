package com.pitang.car.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import com.pitang.car.entities.Car;
import com.pitang.car.repositories.CarRepository;
import com.pitang.common.dtos.cars.CarDTO;
import com.pitang.common.dtos.cars.SaveUpdateCarDTO;
import com.pitang.common.dtos.users.UserDTO;
import com.pitang.common.exceptions.CustomException;
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
		when(carRepository.findAllById(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(car1, car2));

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

	@Test
	void testFindAllCarsByIds() {
		List<Long> carIds = Arrays.asList(car1.getId(), car2.getId());

		when(carRepository.findAllById(carIds)).thenReturn(Arrays.asList(car1, car2));

		List<CarDTO> result = carService.findAllCarsByIds(carIds);

		assertNotNull(result);
		assertEquals(userDTO.getCars().size(), result.size());
		assertEquals(car1.getId(), result.get(0).getId());
		assertEquals(car1.getYear(), result.get(0).getYear());
		assertEquals(car1.getLicensePlate(), result.get(0).getLicensePlate());
		assertEquals(car1.getModel(), result.get(0).getModel());
		assertEquals(car1.getColor(), result.get(0).getColor());

		assertEquals(car2.getId(), result.get(1).getId());
		assertEquals(car2.getYear(), result.get(1).getYear());
		assertEquals(car2.getLicensePlate(), result.get(1).getLicensePlate());
		assertEquals(car2.getModel(), result.get(1).getModel());
		assertEquals(car2.getColor(), result.get(1).getColor());

		verify(carRepository).findAllById(carIds);
	}

	@Test
	void testFindAllCarsByIds_CarsNotFound() {
		List<Long> carIds = Arrays.asList(1L, 2L);

		when(carRepository.findAllById(carIds)).thenReturn(Arrays.asList());

		List<CarDTO> result = carService.findAllCarsByIds(carIds);

		assertNotNull(result);
		assertTrue(result.isEmpty());

		verify(carRepository).findAllById(carIds);
	}

	@Test
	void testFindCarById_CarExistsAndUserHasAccess() {
		when(carRepository.findById(car1.getId())).thenReturn(Optional.of(car1));
		when(userProxy.findUserByLogin(userDTO.getLogin())).thenReturn(userDTO);

		CarDTO result = carService.findCarById(car1.getId(), userDTO.getLogin());

		assertNotNull(result);
		assertEquals(car1.getId(), result.getId());
		assertEquals(car1.getYear(), result.getYear());
		assertEquals(car1.getLicensePlate(), result.getLicensePlate());
		assertEquals(car1.getModel(), result.getModel());
		assertEquals(car1.getColor(), result.getColor());

		verify(carRepository).findById(car1.getId());
		verify(userProxy).findUserByLogin(userDTO.getLogin());
	}

	@Test
	void testFindCarById_CarNotFound() {
		Long carId = 10L;

		when(carRepository.findById(carId)).thenReturn(Optional.empty());

		CarDTO result = carService.findCarById(carId, userDTO.getLogin());

		assertNull(result);

		verify(carRepository).findById(carId);
		verifyNoInteractions(userProxy);
	}

	@Test
	void testFindCarById_UserDoesNotOwnCar() {
		String login = "user1";
		Long carId = car1.getId();

		UserDTO user = new UserDTO(2L, "Maria", "Silva", "maria.silva@gmail.com", new Date(), "mariasilva", "senha123",
				"9876543210", Arrays.asList(carDTO2));

		when(userProxy.findUserByLogin(login)).thenReturn(user);
		when(carRepository.findById(carId)).thenReturn(Optional.of(car1));

		CarDTO result = carService.findCarById(carId, login);

		assertNull(result);

		verify(userProxy).findUserByLogin(login);
		verify(carRepository).findById(carId);
	}

	@Test
	void testFindCarById_UserNotFound() {
		Long carId = car1.getId();
		String login = "userNotFound";

		when(carRepository.findById(carId)).thenReturn(Optional.of(car1));
		when(userProxy.findUserByLogin(login)).thenReturn(null);

		CarDTO result = carService.findCarById(carId, login);

		assertNull(result);

		verify(carRepository).findById(carId);
		verify(userProxy).findUserByLogin(login);
	}

	@Test
	void testInsertCar_Success_WithLogin() {
		SaveUpdateCarDTO newCarDTO = new SaveUpdateCarDTO(2021, "XYZ-5678", "YARIS", "BRANCO");
		String login = "user1";

		when(carRepository.save(any(Car.class))).thenReturn(car2);
		doNothing().when(userProxy).addCarToUser(car2.getId(), login);

		CarDTO result = carService.insertCar(newCarDTO, login);

		assertNotNull(result);
		assertEquals(car2.getId(), result.getId());
		assertEquals(car2.getYear(), result.getYear());
		assertEquals(car2.getLicensePlate(), result.getLicensePlate());
		assertEquals(car2.getModel(), result.getModel());
		assertEquals(car2.getColor(), result.getColor());

		verify(carRepository).save(any(Car.class));
		verify(userProxy).addCarToUser(car2.getId(), login);
	}

	@Test
	void testInsertCar_Success_WithoutLogin() {
		SaveUpdateCarDTO newCarDTO = new SaveUpdateCarDTO(2021, "XYZ-5678", "YARIS", "BRANCO");

		when(carRepository.save(any(Car.class))).thenReturn(car2);

		CarDTO result = carService.insertCar(newCarDTO, null);

		assertNotNull(result);
		assertEquals(car2.getId(), result.getId());
		assertEquals(car2.getYear(), result.getYear());
		assertEquals(car2.getLicensePlate(), result.getLicensePlate());
		assertEquals(car2.getModel(), result.getModel());
		assertEquals(car2.getColor(), result.getColor());

		verify(carRepository).save(any(Car.class));
		verify(userProxy, never()).addCarToUser(anyLong(), anyString());
	}

	@Test
	void testUpdateCar_Success() {
		SaveUpdateCarDTO updateCarDTO = new SaveUpdateCarDTO(2021, "XYZ-5678", "YARIS", "BRANCO");

		when(carRepository.findById(car2.getId())).thenReturn(Optional.of(car1));
		when(userProxy.findUserByLogin(userDTO.getLogin())).thenReturn(userDTO);
		when(carRepository.save(any(Car.class))).thenReturn(car1);

		CarDTO result = carService.updateCar(car2.getId(), updateCarDTO, userDTO.getLogin());

		assertNotNull(result);
		assertEquals(updateCarDTO.getYear(), result.getYear());
		assertEquals(updateCarDTO.getLicensePlate(), result.getLicensePlate());
		assertEquals(updateCarDTO.getModel(), result.getModel());
		assertEquals(updateCarDTO.getColor(), result.getColor());

		verify(carRepository).save(any(Car.class));
	}

	@Test
	void testUpdateCar_CarNotFound() {
		SaveUpdateCarDTO updateCarDTO = new SaveUpdateCarDTO(2021, "XYZ-5678", "YARIS", "BRANCO");

		when(carRepository.findById(car2.getId())).thenReturn(Optional.empty());

		CustomException exception = assertThrows(CustomException.class, () -> {
			carService.updateCar(car2.getId(), updateCarDTO, userDTO.getLogin());
		});

		assertEquals("Invalid fields", exception.getMessage());
		assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
		verify(carRepository, never()).save(any(Car.class));
	}

	@Test
	void testDeleteCar_WithLogin_CarExists() {
		when(carRepository.findById(car1.getId())).thenReturn(Optional.of(car1));
		when(userProxy.findUserByLogin(userDTO.getLogin())).thenReturn(userDTO);

		carService.deleteCar(car1.getId(), userDTO.getLogin());

		verify(carRepository).delete(car1);
		verify(userProxy).deleteCarToUser(car1.getId(), userDTO.getLogin());
	}

	@Test
	void testDeleteCar_WithLogin_CarNotOwnedByUser() {
		UserDTO userDTO2 = new UserDTO(2L, "Maria", "Silva", "maria.silva@gmail.com", new Date(), "mariasilva",
				"senha123", "9876543210", Arrays.asList(carDTO2));

		when(carRepository.findById(car1.getId())).thenReturn(Optional.of(car1));
		when(userProxy.findUserByLogin(userDTO2.getLogin())).thenReturn(userDTO2);

		carService.deleteCar(car1.getId(), userDTO2.getLogin());

		verify(carRepository, never()).delete(car1);
		verify(userProxy, never()).deleteCarToUser(anyLong(), anyString());
	}

	@Test
	void testDeleteCar_WithoutLogin_CarExists() {
		when(carRepository.findById(car1.getId())).thenReturn(Optional.of(car1));

		carService.deleteCar(car1.getId(), null);

		verify(carRepository).delete(car1);
		verify(userProxy, never()).deleteCarToUser(anyLong(), anyString());
	}

	@Test
	void testDeleteCar_CarNotFound() {
		Long carId = 10L;

		when(carRepository.findById(carId)).thenReturn(Optional.empty());

		carService.deleteCar(carId, userDTO.getLogin());

		verify(carRepository, never()).delete(any(Car.class));
		verify(userProxy, never()).deleteCarToUser(anyLong(), anyString());
	}

}
