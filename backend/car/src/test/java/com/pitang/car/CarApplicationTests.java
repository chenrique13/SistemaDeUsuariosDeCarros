package com.pitang.car;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarApplicationTests {

	@Test
	void contextLoads() {
	}

    @Test
    void mainMethodRunsWithoutExceptions() {
        assertDoesNotThrow(() -> CarApplication.main(new String[] {}));
    }
	
}
