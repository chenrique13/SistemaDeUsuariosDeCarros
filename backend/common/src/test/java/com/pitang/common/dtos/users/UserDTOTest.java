package com.pitang.common.dtos.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
import java.util.List;

import com.pitang.common.dtos.cars.CarDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserDTOTest {

    private UserDTO userDTO;
    private Date birthday;

    /**
     * Inicializa os objetos necessários antes de cada teste.
     */
    @BeforeEach
    public void runBeforeEachTest() {
        birthday = new Date();

        userDTO = new UserDTO(
                1L, "Maria", "Silva", "maria.silva@example.com",
                birthday, "mariasilva", "senha123", "1234567890",
                List.of(new CarDTO(1L, 2020, "ABCD1234", "Corolla", "Branco"))
        );
    }

    @Test
    void testDefaultConstructor() {
        UserDTO userDTO = new UserDTO();

        assertNull(userDTO.getId());
        assertNull(userDTO.getFirstName());
        assertNull(userDTO.getLastName());
        assertNull(userDTO.getEmail());
        assertNull(userDTO.getBirthday());
        assertNull(userDTO.getLogin());
        assertNull(userDTO.getPassword());
        assertNull(userDTO.getPhone());
        assertNull(userDTO.getCars());
    }

    @Test
    void testConstructorWithArguments() {
        assertEquals(1L, userDTO.getId());
        assertEquals("Maria", userDTO.getFirstName());
        assertEquals("Silva", userDTO.getLastName());
        assertEquals("maria.silva@example.com", userDTO.getEmail());
        assertEquals(birthday, userDTO.getBirthday());
        assertEquals("mariasilva", userDTO.getLogin());
        assertEquals("senha123", userDTO.getPassword());
        assertEquals("1234567890", userDTO.getPhone());
    }

    @Test
    void testGetId() {
        assertEquals(1L, userDTO.getId());
    }

    @Test
    void testGetFirstName() {
        assertEquals("Maria", userDTO.getFirstName());
    }

    @Test
    void testGetLastName() {
        assertEquals("Silva", userDTO.getLastName());
    }

    @Test
    void testGetEmail() {
        assertEquals("maria.silva@example.com", userDTO.getEmail());
    }

    @Test
    void testGetBirthday() {
        assertEquals(birthday, userDTO.getBirthday());
    }

    @Test
    void testGetLogin() {
        assertEquals("mariasilva", userDTO.getLogin());
    }

    @Test
    void testGetPassword() {
        assertEquals("senha123", userDTO.getPassword());
    }

    @Test
    void testGetPhone() {
        assertEquals("1234567890", userDTO.getPhone());
    }

    @Test
    void testGetCars() {
        assertEquals(1, userDTO.getCars().size());
        CarDTO car = userDTO.getCars().get(0);
        assertEquals(1L, car.getId());
        assertEquals(2020, car.getYear());
        assertEquals("ABCD1234", car.getLicensePlate());
        assertEquals("Corolla", car.getModel());
        assertEquals("Branco", car.getColor());
    }

    /**
     * Testa o método toString. Verifica se a representação textual do {@link UserDTO}
     * é gerada corretamente.
     */
    @Test
    public void testToString() {
        String expectedToString = "UserDTO [id=1, firstName=Maria, lastName=Silva, email=maria.silva@example.com, " +
                "birthday=" + birthday + ", login=mariasilva, password=senha123, phone=1234567890, cars=" + userDTO.getCars() + "]";
        assertEquals(expectedToString, userDTO.toString());
    }
}
