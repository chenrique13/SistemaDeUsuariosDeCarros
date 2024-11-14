package com.pitang.authenticationManager;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthenticationManagerApplicationTests {

	@Test
	void contextLoads() {
	}

    @Test
    void mainMethodRunsWithoutExceptions() {
        assertDoesNotThrow(() -> AuthenticationManagerApplication.main(new String[] {}));
    }
	
}
