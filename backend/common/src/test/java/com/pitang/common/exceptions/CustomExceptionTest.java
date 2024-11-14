package com.pitang.common.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class CustomExceptionTest {

    @Test
    void testConstructorAndGetters() {
        String message = "Unauthorized";
        int errorCode = 1;
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        CustomException exception = new CustomException(message, errorCode, httpStatus);

        assertEquals(message, exception.getMessage());
        assertEquals(errorCode, exception.getErrorCode());
        assertEquals(httpStatus, exception.getHttpStatus());
    }

    @Test
    void testDefaultConstructor() {
        CustomException exception = new CustomException();

        assertNotNull(exception);
    }

    @Test
    void testToJSON() {
        String message = "Unauthorized";
        int errorCode = 1;
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        CustomException exception = new CustomException(message, errorCode, httpStatus);

        String expectedJson = "{\n" +
                              "  \"errorCode\": 1,\n" +
                              "  \"message\": \"Unauthorized\",\n" +
                              "}";
        assertEquals(expectedJson, exception.toJSON());
    }
}
