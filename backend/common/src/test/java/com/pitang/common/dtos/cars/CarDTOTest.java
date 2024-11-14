package com.pitang.common.dtos.cars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarDTOTest {

	private CarDTO carDTO;

	/**
	 * Inicializa os objetos necessários antes de cada teste.
	 */
	@BeforeEach
	public void runBeforeEachTest() {
		carDTO = new CarDTO(1L, 2020, "ABC-1234", "ONIX", "Preto");
	}

	@Test
	void testDefaultConstructor() {
		CarDTO carDTO = new CarDTO();

		assertNull(carDTO.getId());
		assertEquals(0, carDTO.getYear());
		assertNull(carDTO.getLicensePlate());
		assertNull(carDTO.getModel());
		assertNull(carDTO.getColor());
	}

	@Test
	void testConstructorWithArguments() {
		assertEquals(1L, carDTO.getId());
		assertEquals(2020, carDTO.getYear());
		assertEquals("ABC-1234", carDTO.getLicensePlate());
		assertEquals("ONIX", carDTO.getModel());
		assertEquals("Preto", carDTO.getColor());
	}

	@Test
	void testGetId() {
		assertEquals(1L, carDTO.getId());
	}

	@Test
	void testGetYear() {
		assertEquals(2020, carDTO.getYear());
	}

	@Test
	void testGetLicensePlate() {
		assertEquals("ABC-1234", carDTO.getLicensePlate());
	}

	@Test
	void testGetModel() {
		assertEquals("ONIX", carDTO.getModel());
	}

	@Test
	void testGetColor() {
		assertEquals("Preto", carDTO.getColor());
	}

	/**
	 * Testa o método toString. Verifica se a representação textual do {@link CarDTO}
	 * é gerada corretamente.
	 */
	@Test
	public void testToString() {
		String expectedToString = "CarDTO [id=1, year=2020, licensePlate=ABC-1234, model=ONIX, color=Preto]";
		assertEquals(expectedToString, carDTO.toString());
	}
}
