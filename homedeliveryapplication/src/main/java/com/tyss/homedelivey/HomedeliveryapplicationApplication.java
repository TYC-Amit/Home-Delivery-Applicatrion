package com.tyss.homedelivey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition /* localhost:8091/swagger-ui/index.html */
public class HomedeliveryapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomedeliveryapplicationApplication.class, args);
	}

}
