package com.pitang.user.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

	private User user;
	private List<Long> listCars;

	/**
	 * Inicializa os objetos necessarios antes de cada teste.
	 */
	@BeforeEach
	public void runBeforeEachTest() {
		listCars = new ArrayList<Long>();
		listCars.add(1L);
		user = new User(1L, "Carlos", "Pereira", "teste@gmail.com",
				Date.from(LocalDate.of(1994, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()), "cPereira",
				"teste", "81988888888", listCars);
	}

	/**
	 * Testa o construtor sem argumentos. Verifica se o objeto inicializa
	 * corretamente.
	 */
	@Test
	public void testConstructorWithoutArguments() {
		User user = new User();
		assertNull(user.getId());
		assertNull(user.getFirstName());
		assertNull(user.getLastName());
		assertNull(user.getEmail());
		assertNull(user.getBirthday());
		assertNull(user.getLogin());
		assertNull(user.getPassword());
		assertNull(user.getPhone());
		assertNull(user.getCars());
	}

	/**
	 * Testa o construtor com argumentos. Verifica se o objeto inicializa com os
	 * valores fornecidos.
	 */
	@Test
	public void testConstructorWithArguments() {
		assertEquals(1L, user.getId());
		assertEquals("Carlos", user.getFirstName());
		assertEquals("Pereira", user.getLastName());
		assertEquals("teste@gmail.com", user.getEmail());
		assertEquals(Date.from(LocalDate.of(1994, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				user.getBirthday());
		assertEquals("cPereira", user.getLogin());
		assertEquals("teste", user.getPassword());
		assertEquals("81988888888", user.getPhone());
		assertEquals(listCars, user.getCars());
	}

	/**
	 * Testa os metodos get e set para o id. Verifica se obtem e define o id
	 * corretamente.
	 */
	@Test
	public void testGetSetId() {
		user.setId(Long.valueOf(2));
		assertEquals(Long.valueOf(2), user.getId());
	}

	/**
	 * Testa os metodos get e set para o nome. Verifica se obtem e define o nome
	 * corretamente.
	 */
	@Test
	public void testGetSetFirstName() {
		user.setFirstName("Henrique");
		assertEquals("Henrique", user.getFirstName());
	}

	/**
	 * Testa os metodos get e set para o sobrenome. Verifica se obtem e define o
	 * sobrenome corretamente.
	 */
	@Test
	public void testGetSetLastName() {
		user.setLastName("Silva");
		assertEquals("Silva", user.getLastName());
	}

	/**
	 * Testa os metodos get e set para o email. Verifica se obtem e define o email
	 * corretamente.
	 */
	@Test
	public void testGetSetEmail() {
		user.setEmail("teste2@gmail.com");
		assertEquals("teste2@gmail.com", user.getEmail());
	}

	/**
	 * Testa os metodos get e set para a data de nascimento. Verifica se obtem e
	 * define a data de nascimento corretamente.
	 */
	@Test
	public void testGetSetBirthday() {
		user.setBirthday(Date.from(LocalDate.of(2020, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		assertEquals(Date.from(LocalDate.of(2020, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				user.getBirthday());
	}

	/**
	 * Testa os metodos get e set para o login. Verifica se obtem e define o login
	 * corretamente.
	 */
	@Test
	public void testGetSetLogin() {
		user.setLogin("hSilva");
		assertEquals("hSilva", user.getLogin());
	}

	/**
	 * Testa os metodos get e set para o password. Verifica se obtem e define o
	 * password corretamente.
	 */
	@Test
	public void testGetSetPassword() {
		user.setPassword("teste2");
		assertEquals("teste2", user.getPassword());
	}

	/**
	 * Testa os metodos get e set para o telefone. Verifica se obtem e define o
	 * telefone corretamente.
	 */
	@Test
	public void testGetSetPhone() {
		user.setPhone("81999999999");
		assertEquals("81999999999", user.getPhone());
	}

	/**
	 * Testa os metodos get e set para a lista de ids dos carros. Verifica se obtem
	 * e define a lista de ids dos carros corretamente.
	 */
	@Test
	public void testGetSetCars() {
		user.setCars(new ArrayList<Long>());
		assertEquals(new ArrayList<Long>(), user.getCars());
	}

	/**
	 * Testa os metodos get e set para a data de criacao. Verifica se obtem e define
	 * o telefone corretamente.
	 */
	@Test
	public void testGetSetCreatedAt() {
		user.setCreatedAt(LocalDateTime.of(2020, 1, 1, 0, 0));
		assertEquals(LocalDateTime.of(2020, 1, 1, 0, 0), user.getCreatedAt());
	}

	/**
	 * Testa os metodos get e set para o ultimo login antes do atual. Verifica se obtem e define o
	 * telefone corretamente.
	 */
	@Test
	public void testGetSetLastLogin() {
		user.setLastLogin(LocalDateTime.of(2020, 1, 1, 0, 0));
		assertEquals(LocalDateTime.of(2020, 1, 1, 0, 0), user.getLastLogin());
	}
	
	/**
	 * Testa os metodos get e set para o login atual. Verifica se obtem e define o
	 * telefone corretamente.
	 */
	@Test
	public void testGetSetCurrentLogin() {
		user.setCurrentLogin(LocalDateTime.of(2020, 1, 1, 0, 0));
		assertEquals(LocalDateTime.of(2020, 1, 1, 0, 0), user.getCurrentLogin());
	}

	
}
