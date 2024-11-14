package com.pitang.common.dtos.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateUserDTOTest {

	private UpdateUserDTO updateUserDTO;
	private Date birthday;

	/**
	 * Inicializa os objetos necessários antes de cada teste.
	 */
	@BeforeEach
	public void runBeforeEachTest() {
		birthday = new Date();

		updateUserDTO = new UpdateUserDTO("Carlos", "Silva", "carlos.silva@example.com", birthday, "carlossilva",
				"senha123", "1234567890");
	}

	@Test
	void testDefaultConstructor() {
		UpdateUserDTO updateUserDTO = new UpdateUserDTO();

		assertNull(updateUserDTO.getFirstName());
		assertNull(updateUserDTO.getLastName());
		assertNull(updateUserDTO.getEmail());
		assertNull(updateUserDTO.getBirthday());
		assertNull(updateUserDTO.getLogin());
		assertNull(updateUserDTO.getPassword());
		assertNull(updateUserDTO.getPhone());
	}

	@Test
	void testConstructorWithArguments() {
		assertEquals("Carlos", updateUserDTO.getFirstName());
		assertEquals("Silva", updateUserDTO.getLastName());
		assertEquals("carlos.silva@example.com", updateUserDTO.getEmail());
		assertEquals(birthday, updateUserDTO.getBirthday());
		assertEquals("carlossilva", updateUserDTO.getLogin());
		assertEquals("senha123", updateUserDTO.getPassword());
		assertEquals("1234567890", updateUserDTO.getPhone());
	}

	@Test
	void testGetFirstName() {
		assertEquals("Carlos", updateUserDTO.getFirstName());
	}

	@Test
	void testGetLastName() {
		assertEquals("Silva", updateUserDTO.getLastName());
	}

	@Test
	void testGetEmail() {
		assertEquals("carlos.silva@example.com", updateUserDTO.getEmail());
	}

	@Test
	void testGetBirthday() {
		assertEquals(birthday, updateUserDTO.getBirthday());
	}

	@Test
	void testGetLogin() {
		assertEquals("carlossilva", updateUserDTO.getLogin());
	}

	@Test
	void testGetPassword() {
		assertEquals("senha123", updateUserDTO.getPassword());
	}

	@Test
	void testGetPhone() {
		assertEquals("1234567890", updateUserDTO.getPhone());
	}

	/**
	 * Testa o método toString. Verifica se a representação textual do
	 * {@link UpdateUserDTO} é gerada corretamente.
	 */
	@Test
	public void testToString() {
		String expectedToString = "UpdateUserDTO [firstName=Carlos, lastName=Silva, email=carlos.silva@example.com, "
				+ "birthday=" + birthday + ", login=carlossilva, password=senha123, phone=1234567890]";
		assertEquals(expectedToString, updateUserDTO.toString());
	}
}
