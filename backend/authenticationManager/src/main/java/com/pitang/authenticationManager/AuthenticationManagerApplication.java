package com.pitang.authenticationManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.pitang.common.proxies")
@ComponentScan(basePackages = { "com.pitang.authenticationManager", "com.pitang.common.utils" })
public class AuthenticationManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationManagerApplication.class, args);
	}

}
