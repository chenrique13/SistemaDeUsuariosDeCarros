package com.pitang.user.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
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

}
