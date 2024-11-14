package com.pitang.common;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommonApplicationTests {

	@Test
	void contextLoads() {
	}

    @Test
    void mainMethodRunsWithoutExceptions() {
        assertDoesNotThrow(() -> CommonApplication.main(new String[] {}));
    }
	
}
