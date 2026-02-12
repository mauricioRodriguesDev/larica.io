package com.example.larica;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Larica.io API",
		version = "v1",
		description = "API para o agregador de descoberta de comida Larica.io, focado na Zona Sul do Rio de Janeiro."
))
public class LaricaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaricaApplication.class, args);
	}

}
