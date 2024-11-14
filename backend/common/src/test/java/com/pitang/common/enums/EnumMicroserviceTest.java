package com.pitang.common.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EnumMicroserviceTest {

    @Test
    void testGetName() {
        assertEquals("user-service", EnumMicroservice.USERS.getName());
        assertEquals("car-service", EnumMicroservice.CARS.getName());
    }

    @Test
    void testEnumValues() {
        EnumMicroservice[] values = EnumMicroservice.values();
        
        assertEquals(2, values.length);
        assertEquals(EnumMicroservice.USERS, values[0]);
        assertEquals(EnumMicroservice.CARS, values[1]);
    }

    @Test
    void testEnumName() {
        assertEquals("USERS", EnumMicroservice.USERS.name());
        assertEquals("CARS", EnumMicroservice.CARS.name());
    }
}