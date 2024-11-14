package com.pitang.common.dtos.cars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SaveUpdateCarDTOTest {

	private SaveUpdateCarDTO saveUpdateCarDTO;

	/**
	 * Inicializa os objetos necessários antes de cada teste.
	 */
	@BeforeEach
	public void runBeforeEachTest() {
		saveUpdateCarDTO = new SaveUpdateCarDTO(2021, "XYZ-5678", "Corolla", "Branco");
	}

	@Test
	void testDefaultConstructor() {
		SaveUpdateCarDTO saveUpdateCarDTO = new SaveUpdateCarDTO();

		assertEquals(0, saveUpdateCarDTO.getYear());
		assertNull(saveUpdateCarDTO.getLicensePlate());
		assertNull(saveUpdateCarDTO.getModel());
		assertNull(saveUpdateCarDTO.getColor());
	}

	@Test
	void testConstructorWithArguments() {
		assertEquals(2021, saveUpdateCarDTO.getYear());
		assertEquals("XYZ-5678", saveUpdateCarDTO.getLicensePlate());
		assertEquals("Corolla", saveUpdateCarDTO.getModel());
		assertEquals("Branco", saveUpdateCarDTO.getColor());
	}

	@Test
	void testGetYear() {
		assertEquals(2021, saveUpdateCarDTO.getYear());
	}

	@Test
	void testGetLicensePlate() {
		assertEquals("XYZ-5678", saveUpdateCarDTO.getLicensePlate());
	}

	@Test
	void testGetModel() {
		assertEquals("Corolla", saveUpdateCarDTO.getModel());
	}

	@Test
	void testGetColor() {
		assertEquals("Branco", saveUpdateCarDTO.getColor());
	}

	/**
	 * Testa o método toString. Verifica se a representação textual do {@link SaveUpdateCarDTO}
	 * é gerada corretamente.
	 */
	@Test
	public void testToString() {
		String expectedToString = "SaveUpdateCarDTO [year=2021, licensePlate=XYZ-5678, model=Corolla, color=Branco]";
		assertEquals(expectedToString, saveUpdateCarDTO.toString());
	}
}
