package com.pitang.common.dtos.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ErrorDTOTest {

	private ErrorDTO errorDTO;

	/**
	 * Inicializa os objetos necessários antes de cada teste.
	 */
	@BeforeEach
	public void runBeforeEachTest() {
		errorDTO = new ErrorDTO("Unauthorized", 1);
	}

	@Test
	void testDefaultConstructor() {
		ErrorDTO errorDTO = new ErrorDTO();

		assertNull(errorDTO.getMessage());
		assertEquals(0, errorDTO.getErrorCode());
	}

	@Test
	void testConstructorWithArguments() {
		assertEquals("Unauthorized", errorDTO.getMessage());
		assertEquals(1, errorDTO.getErrorCode());
	}

	@Test
	void testGetMessage() {
		assertEquals("Unauthorized", errorDTO.getMessage());
	}

	@Test
	void testGetErrorCode() {
		assertEquals(1, errorDTO.getErrorCode());
	}

	/**
	 * Testa o método toString. Verifica se a representação textual do {@link ErrorDTO}
	 * é gerada corretamente.
	 */
	@Test
	public void testToString() {
		String expectedToString = "ErrorDTO [message=Unauthorized, errorCode=1]";
		assertEquals(expectedToString, errorDTO.toString());
	}
}
