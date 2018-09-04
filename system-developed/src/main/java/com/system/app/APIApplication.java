package com.system.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan("com.system.controller")
@EnableWebMvc
public class APIApplication {
	public static void main(String[] args) {
		SpringApplication.run(APIApplication.class, args);
	}

}
