package com.pitang.car.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarTest {

	private Car car;

	/**
	 * Inicializa os objetos necessarios antes de cada teste.
	 */
	@BeforeEach
	public void runBeforeEachTest() {
		car = new Car(1L, 2024, "OYS7809", "YARIS", "PRETO");
	}

	/**
	 * Testa o construtor sem argumentos. Verifica se o objeto inicializa
	 * corretamente.
	 */
	@Test
	public void testConstructorWithoutArguments() {
		Car carro = new Car();
		assertNull(carro.getId());
		assertNull(carro.getLicensePlate());
		assertNull(carro.getModel());
		assertNull(carro.getColor());
		assertEquals(0, carro.getYear());
	}

	/**
	 * Testa o construtor com argumentos. Verifica se o objeto inicializa com os
	 * valores fornecidos.
	 */
	@Test
	public void testConstructorWithArguments() {
		assertEquals(1L, car.getId());
		assertEquals(2024, car.getYear());
		assertEquals("OYS7809", car.getLicensePlate());
		assertEquals("YARIS", car.getModel());
		assertEquals("PRETO", car.getColor());
	}

	/**
	 * Testa os metodos get e set para o id. Verifica se obtem e define o id
	 * corretamente.
	 */
	@Test
	public void testGetSetId() {
		car.setId(2L);
		assertEquals(2L, car.getId());
	}

	/**
	 * Testa os metodos get e set para o ano de fabricacao. Verifica se obtem e
	 * define o ano de fabricacao corretamente.
	 */
	@Test
	public void testGetSetYear() {
		car.setYear(2025);
		assertEquals(2025, car.getYear());
	}

	/**
	 * Testa os metodos get e set para a placa. Verifica se obtem e define a placa
	 * corretamente.
	 */
	@Test
	public void testGetSetLicensePlate() {
		car.setLicensePlate("KIC2050");
		assertEquals("KIC2050", car.getLicensePlate());
	}

	/**
	 * Testa os metodos get e set para o modelo. Verifica se obtem e define o modelo
	 * corretamente.
	 */
	@Test
	public void testGetSetModel() {
		car.setModel("COROLLA");
		assertEquals("COROLLA", car.getModel());
	}

	/**
	 * Testa os metodos get e set para a cor. Verifica se obtem e define a cor
	 * corretamente.
	 */
	@Test
	public void testGetSetColor() {
		car.setColor("BRANCO");
		assertEquals("BRANCO", car.getColor());
	}

	/**
	 * Testa o metodo isValid esperando o retorno verdadeiro.
	 */
	@Test
	public void testIsValid_AllFieldsValid_ReturnsTrue() {
		assertTrue(car.isValid(), "Expected isValid() to return true when all fields are valid.");
	}

	/**
	 * Testa o atributo model nulo ou vazio no metodo isValid esperando o retorno
	 * false.
	 */
	@Test
	public void testIsValid_NullAndEmptyFirstName_ReturnsFalse() {
		car.setModel(null);
		assertFalse(car.isValid(), "Expected isValid() to return false when firstName is null.");

		car.setModel(" ");
		assertFalse(car.isValid(), "Expected isValid() to return false when firstName is empty.");
	}

	/**
	 * Testa o atributo licensePlate nulo ou vazio no metodo isValid esperando o
	 * retorno false.
	 */
	@Test
	public void testIsValid_NullAndEmptyLastName_ReturnsFalse() {
		car.setLicensePlate(null);
		assertFalse(car.isValid(), "Expected isValid() to return false when lastName is null.");

		car.setLicensePlate(" ");
		assertFalse(car.isValid(), "Expected isValid() to return false when lastName is empty.");
	}

	/**
	 * Testa o atributo color nulo ou vazio no metodo isValid esperando o retorno
	 * false.
	 */
	@Test
	public void testIsValid_NullAndEmptyEmail_ReturnsFalse() {
		car.setColor(null);
		assertFalse(car.isValid(), "Expected isValid() to return false when email is null.");

		car.setColor(" ");
		assertFalse(car.isValid(), "Expected isValid() to return false when email is empty.");
	}

	/**
	 * Testa o metodo equals. Verifica se dois objetos Car sao considerados iguais
	 * com base no id e licensePlate.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals() {
		Car car2 = new Car(1L, 2024, "OYS7809", "YARIS", "PRETO");
		Car car3 = new Car(3L, 2025, "AAA4321", "PALIO", "CINZA");

		assertTrue(car.equals(car));
		assertFalse(car.equals(null));
		assertFalse(car.equals("OutraClasse"));
		assertTrue(car.equals(car2));
		assertFalse(car.equals(car3));
	}

	/**
	 * Testa o metodo hashCode. Verifica se o hashCode é gerado corretamente com
	 * base no id e licensePlate.
	 */
	@Test
	public void testHashCode() {
		Car car4 = new Car(1L, 2024, "OYS7809", "YARIS", "PRETO");

		assertEquals(car.hashCode(), car4.hashCode());
	}

	/**
	 * Testa o metodo toString. Verifica se a representacao textual do {@link Car} é
	 * gerada corretamente.
	 */
	@Test
	public void testToString() {
		String toString = "Car [id=1, year=2024, licensePlate=OYS7809, model=YARIS, color=PRETO]";
		assertEquals(toString, car.toString());
	}

}
