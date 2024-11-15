package com.pitang.common.dtos.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pitang.common.dtos.cars.SaveUpdateCarDTO;

class SaveUserDTOTest {

	private SaveUserDTO saveUserDTO;
	private Date birthday;
	private List<SaveUpdateCarDTO> cars;

	/**
	 * Inicializa os objetos necessários antes de cada teste.
	 */
	@BeforeEach
	public void runBeforeEachTest() {
		birthday = new Date();
		cars = Arrays.asList(new SaveUpdateCarDTO(2020, "ABC-1234", "ONIX", "Preto"),
				new SaveUpdateCarDTO(2021, "XYZ-5678", "YARIS", "Branco"));

		saveUserDTO = new SaveUserDTO("Carlos", "Silva", "carlos.silva@example.com", birthday, "carlossilva",
				"senha123", "1234567890", cars);
	}

	@Test
	void testDefaultConstructor() {
		SaveUserDTO saveUserDTO = new SaveUserDTO();

		assertNull(saveUserDTO.getFirstName());
		assertNull(saveUserDTO.getLastName());
		assertNull(saveUserDTO.getEmail());
		assertNull(saveUserDTO.getBirthday());
		assertNull(saveUserDTO.getLogin());
		assertNull(saveUserDTO.getPassword());
		assertNull(saveUserDTO.getPhone());
		assertNull(saveUserDTO.getCars());
	}

	@Test
	void testConstructorWithArguments() {
		assertEquals("Carlos", saveUserDTO.getFirstName());
		assertEquals("Silva", saveUserDTO.getLastName());
		assertEquals("carlos.silva@example.com", saveUserDTO.getEmail());
		assertEquals(birthday, saveUserDTO.getBirthday());
		assertEquals("carlossilva", saveUserDTO.getLogin());
		assertEquals("senha123", saveUserDTO.getPassword());
		assertEquals("1234567890", saveUserDTO.getPhone());
		assertEquals(cars, saveUserDTO.getCars());
	}

	@Test
	void testGetFirstName() {
		assertEquals("Carlos", saveUserDTO.getFirstName());
	}

	@Test
	void testGetLastName() {
		assertEquals("Silva", saveUserDTO.getLastName());
	}

	@Test
	void testGetEmail() {
		assertEquals("carlos.silva@example.com", saveUserDTO.getEmail());
	}

	@Test
	void testGetBirthday() {
		assertEquals(birthday, saveUserDTO.getBirthday());
	}

	@Test
	void testGetLogin() {
		assertEquals("carlossilva", saveUserDTO.getLogin());
	}

	@Test
	void testGetPassword() {
		assertEquals("senha123", saveUserDTO.getPassword());
	}

	@Test
	void testGetPhone() {
		assertEquals("1234567890", saveUserDTO.getPhone());
	}

	@Test
	void testGetCars() {
		assertEquals(cars, saveUserDTO.getCars());
	}

	/**
	 * Testa o método toString. Verifica se a representação textual do
	 * {@link SaveUserDTO} é gerada corretamente.
	 */
	@Test
	public void testToString() {
		String expectedToString = "SaveUserDTO [firstName=Carlos, lastName=Silva, email=carlos.silva@example.com, "
				+ "birthday=" + birthday + ", login=carlossilva, password=senha123, phone=1234567890, cars=" + cars
				+ "]";
		assertEquals(expectedToString, saveUserDTO.toString());
	}
}
