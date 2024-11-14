package com.pitang.common.dtos.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SigninDTOTest {

	private SigninDTO signinDTO;

	/**
	 * Inicializa os objetos necessarios antes de cada teste.
	 */
	@BeforeEach
	public void runBeforeEachTest() {
		signinDTO = new SigninDTO("Carlos", "password123");
	}

	@Test
	void testDefaultConstructor() {
		SigninDTO signinDTO = new SigninDTO();

		assertNull(signinDTO.getLogin());
		assertNull(signinDTO.getPassword());
	}

	@Test
	void testConstructorWithArguments() {
		assertEquals("Carlos", signinDTO.getLogin());
		assertEquals("password123", signinDTO.getPassword());
	}

	@Test
	void testGetLogin() {
		assertEquals("Carlos", signinDTO.getLogin());
	}

	@Test
	void testGetPassword() {
		assertEquals("password123", signinDTO.getPassword());
	}

	/**
	 * Testa o metodo toString. Verifica se a representacao textual do {@link User}
	 * é gerada corretamente.
	 */
	@Test
	public void testToString() {
		String toString = "SigninDTO [login=Carlos, password=password123]";
		assertEquals(toString, signinDTO.toString());
	}

}
