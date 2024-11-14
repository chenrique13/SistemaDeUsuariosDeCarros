package com.pitang.car.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarTest {

	private Car carro;

	/**
	 * Inicializa os objetos necessarios antes de cada teste.
	 */
	@BeforeEach
	public void runBeforeEachTest() {
		carro = new Car(1L, 2024, "OYS7809", "YARIS", "PRETO");
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
		assertEquals(1L, carro.getId());
		assertEquals(2024, carro.getYear());
		assertEquals("OYS7809", carro.getLicensePlate());
		assertEquals("YARIS", carro.getModel());
		assertEquals("PRETO", carro.getColor());
	}

    /**
     * Testa os metodos get e set para o id.
     * Verifica se obtem e define o id corretamente.
     */
	@Test
	public void testGetSetId() {
		carro.setId(2L);
		assertEquals(2L, carro.getId());
	}

    /**
     * Testa os metodos get e set para o ano de fabricacao.
     * Verifica se obtem e define o ano de fabricacao corretamente.
     */
	@Test
	public void testGetSetYear() {
		carro.setYear(2025);
		assertEquals(2025, carro.getYear());
	}

    /**
     * Testa os metodos get e set para a placa.
     * Verifica se obtem e define a placa corretamente.
     */
	@Test
	public void testGetSetLicensePlate() {
		carro.setLicensePlate("KIC2050");
		assertEquals("KIC2050", carro.getLicensePlate());
	}

    /**
     * Testa os metodos get e set para o modelo.
     * Verifica se obtem e define o modelo corretamente.
     */
	@Test
	public void testGetSetModel() {
		carro.setModel("COROLLA");
		assertEquals("COROLLA", carro.getModel());
	}

    /**
     * Testa os metodos get e set para a cor.
     * Verifica se obtem e define a cor corretamente.
     */
	@Test
	public void testGetSetColor() {
		carro.setColor("BRANCO");
		assertEquals("BRANCO", carro.getColor());
	}
	
    /**
     * Testa o metodo equals.
     * Verifica se dois objetos Car sao considerados iguais
     * com base no id e licensePlate.
     */
    @SuppressWarnings("unlikely-arg-type")
	@Test
    public void testEquals() {
    	Car carro2 = new Car(1L, 2024, "OYS7809", "YARIS", "PRETO");
    	Car carro3 = new Car(3L, 2025, "AAA4321", "PALIO", "CINZA");

        assertTrue(carro.equals(carro));
        assertFalse(carro.equals(null));
        assertFalse(carro.equals("OutraClasse"));
        assertTrue(carro.equals(carro2));
        assertFalse(carro.equals(carro3));
    }

    /**
     * Testa o metodo hashCode.
     * Verifica se o hashCode é gerado corretamente com base no id e licensePlate.
     */
    @Test
    public void testHashCode() {
    	Car carro4 = new Car(1L, 2024, "OYS7809", "YARIS", "PRETO");

        assertEquals(carro.hashCode(), carro4.hashCode());
    }

    /**
     * Testa o metodo toString.
     * Verifica se a representacao textual do {@link Car} é gerada corretamente.
     */
    @Test
    public void testToString() {
        String representacaoTextual = "Car [id=1, year=2024, licensePlate=OYS7809, model=YARIS, color=PRETO]";
        assertEquals(representacaoTextual, carro.toString());
    }
	
}
