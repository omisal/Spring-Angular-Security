package com.example.securityBack;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@OpenAPIDefinition(info =@Info(title = "Spring Angular sucurity project API Documentation"))
public class SecurityBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityBackApplication.class, args);
	}

}