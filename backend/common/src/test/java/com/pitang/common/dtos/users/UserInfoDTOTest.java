package com.pitang.common.dtos.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.pitang.common.dtos.cars.CarDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserInfoDTOTest {

    private UserInfoDTO userInfoDTO;
    private Date birthday;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;

    /**
     * Inicializa os objetos necessários antes de cada teste.
     */
    @BeforeEach
    public void runBeforeEachTest() {
        birthday = new Date();
        createdAt = LocalDateTime.now();
        lastLogin = LocalDateTime.now();

        userInfoDTO = new UserInfoDTO(
                "Maria", "Silva", "maria.silva@example.com",
                birthday, "mariasilva", "senha123", "1234567890",
                List.of(new CarDTO(1L, 2020, "ABCD1234", "Corolla", "Branco")),
                createdAt, lastLogin
        );
    }

    @Test
    void testDefaultConstructor() {
        UserInfoDTO userInfoDTO = new UserInfoDTO();

        assertNull(userInfoDTO.getFirstName());
        assertNull(userInfoDTO.getLastName());
        assertNull(userInfoDTO.getEmail());
        assertNull(userInfoDTO.getBirthday());
        assertNull(userInfoDTO.getLogin());
        assertNull(userInfoDTO.getPhone());
        assertNull(userInfoDTO.getCars());
        assertNull(userInfoDTO.getCreatedAt());
        assertNull(userInfoDTO.getLastLogin());
    }

    @Test
    void testConstructorWithArguments() {
        assertEquals("Maria", userInfoDTO.getFirstName());
        assertEquals("Silva", userInfoDTO.getLastName());
        assertEquals("maria.silva@example.com", userInfoDTO.getEmail());
        assertEquals(birthday, userInfoDTO.getBirthday());
        assertEquals("mariasilva", userInfoDTO.getLogin());
        assertEquals("1234567890", userInfoDTO.getPhone());
        assertEquals(createdAt, userInfoDTO.getCreatedAt());
        assertEquals(lastLogin, userInfoDTO.getLastLogin());
    }

    @Test
    void testGetFirstName() {
        assertEquals("Maria", userInfoDTO.getFirstName());
    }

    @Test
    void testGetLastName() {
        assertEquals("Silva", userInfoDTO.getLastName());
    }

    @Test
    void testGetEmail() {
        assertEquals("maria.silva@example.com", userInfoDTO.getEmail());
    }

    @Test
    void testGetBirthday() {
        assertEquals(birthday, userInfoDTO.getBirthday());
    }

    @Test
    void testGetLogin() {
        assertEquals("mariasilva", userInfoDTO.getLogin());
    }

    @Test
    void testGetPhone() {
        assertEquals("1234567890", userInfoDTO.getPhone());
    }

    @Test
    void testGetCars() {
        assertEquals(1, userInfoDTO.getCars().size());
        CarDTO car = userInfoDTO.getCars().get(0);
        assertEquals(1L, car.getId());
        assertEquals(2020, car.getYear());
        assertEquals("ABCD1234", car.getLicensePlate());
        assertEquals("Corolla", car.getModel());
        assertEquals("Branco", car.getColor());
    }

    @Test
    void testGetCreatedAt() {
        assertEquals(createdAt, userInfoDTO.getCreatedAt());
    }

    @Test
    void testGetLastLogin() {
        assertEquals(lastLogin, userInfoDTO.getLastLogin());
    }

    /**
     * Testa o método toString. Verifica se a representação textual do {@link UserInfoDTO}
     * é gerada corretamente.
     */
    @Test
    public void testToString() {
        String expectedToString = "UserInfoDTO [firstName=Maria, lastName=Silva, email=maria.silva@example.com, " +
                "birthday=" + birthday + ", login=mariasilva, phone=1234567890, cars=" + userInfoDTO.getCars() +
                ", createdAt=" + createdAt + ", lastLogin=" + lastLogin + "]";
        assertEquals(expectedToString, userInfoDTO.toString());
    }
}
