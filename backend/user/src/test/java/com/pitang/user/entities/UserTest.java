package com.pitang.user.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


	/**
	 * Testa o metodo isValid esperando o retorno verdadeiro.
	 */
    @Test
    public void testIsValid_AllFieldsValid_ReturnsTrue() {
        assertTrue(user.isValid(), "Expected isValid() to return true when all fields are valid.");
    }

	/**
	 * Testa o atributo firstName nulo ou vazio no metodo isValid esperando o
	 * retorno false.
	 */
    @Test
    public void testIsValid_NullAndEmptyFirstName_ReturnsFalse() {
        user.setFirstName(null);
        assertFalse(user.isValid(), "Expected isValid() to return false when firstName is null.");
        
        user.setFirstName(" ");
        assertFalse(user.isValid(), "Expected isValid() to return false when firstName is empty.");
    }

	/**
	 * Testa o atributo lastName nulo ou vazio no metodo isValid esperando o
	 * retorno false.
	 */
    @Test
    public void testIsValid_NullAndEmptyLastName_ReturnsFalse() {
        user.setLastName(null);
        assertFalse(user.isValid(), "Expected isValid() to return false when lastName is null.");
        
        user.setLastName(" ");
        assertFalse(user.isValid(), "Expected isValid() to return false when lastName is empty.");
    }

	/**
	 * Testa o atributo email nulo ou vazio no metodo isValid esperando o
	 * retorno false.
	 */
    @Test
    public void testIsValid_NullAndEmptyEmail_ReturnsFalse() {
        user.setEmail(null);
        assertFalse(user.isValid(), "Expected isValid() to return false when email is null.");
        
        user.setEmail(" ");
        assertFalse(user.isValid(), "Expected isValid() to return false when email is empty.");
    }

	/**
	 * Testa o atributo birthday nulo no metodo isValid esperando o
	 * retorno false.
	 */
    @Test
    public void testIsValid_NullBirthday_ReturnsFalse() {
        user.setBirthday(null);
        assertFalse(user.isValid(), "Expected isValid() to return false when birthday is null.");
    }

	/**
	 * Testa o atributo login nulo ou vazio no metodo isValid esperando o
	 * retorno false.
	 */
    @Test
    public void testIsValid_NullAndEmptyLogin_ReturnsFalse() {
        user.setLogin(null);
        assertFalse(user.isValid(), "Expected isValid() to return false when login is null.");
        
        user.setLogin(" ");
        assertFalse(user.isValid(), "Expected isValid() to return false when login is empty.");
    }

	/**
	 * Testa o atributo password nulo ou vazio no metodo isValid esperando o
	 * retorno false.
	 */
    @Test
    public void testIsValid_NullAndEmptyPassword_ReturnsFalse() {
        user.setPassword(null);
        assertFalse(user.isValid(), "Expected isValid() to return false when password is null.");
        
        user.setPassword(" ");
        assertFalse(user.isValid(), "Expected isValid() to return false when password is empty.");
    }

	/**
	 * Testa o atributo phone nulo ou vazio no metodo isValid esperando o
	 * retorno false.
	 */
    @Test
    public void testIsValid_NullAndEmptyPhone_ReturnsFalse() {
        user.setPhone(null);
        assertFalse(user.isValid(), "Expected isValid() to return false when phone is null.");
        
        user.setPhone(" ");
        assertFalse(user.isValid(), "Expected isValid() to return false when phone is empty.");
    }
	
	/**
	 * Testa o metodo equals. Verifica se dois objetos {@link User} sao considerados
	 * iguais com base no id, email e login.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals() {
		User user2 = new User(1L, "Carlos", "Pereira", "teste@gmail.com",
				Date.from(LocalDate.of(1994, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()), "cPereira",
				"teste", "81988888888", listCars);
		User user3 = new User(3L, "Henrique", "Silva", "teste3@gmail.com",
				Date.from(LocalDate.of(2000, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()), "hSilva",
				"teste3", "81999999999", listCars);

		assertTrue(user.equals(user));
		assertFalse(user.equals(null));
		assertFalse(user.equals("OutraClasse"));
		assertTrue(user.equals(user2));
		assertFalse(user.equals(user3));
	}

	/**
	 * Testa o metodo hashCode. Verifica se o hashCode é gerado corretamente com
	 * base no id, email e login.
	 */
	@Test
	public void testHashCode() {
		User user4 = new User(1L, "Carlos", "Pereira", "teste@gmail.com",
				Date.from(LocalDate.of(1994, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()), "cPereira",
				"teste", "81988888888", listCars);

		assertEquals(user.hashCode(), user4.hashCode());
	}

	/**
	 * Testa o metodo toString. Verifica se a representacao textual do {@link User}
	 * é gerada corretamente.
	 */
	@Test
	public void testToString() {
		String toString = "User [id=1, firstName=Carlos, lastName=Pereira, email=teste@gmail.com, birthday=Sat Jan 01 00:00:00 BRST 1994, login=cPereira, password=teste, phone=81988888888, cars=[1]]";
		assertEquals(toString, user.toString());
	}
}
