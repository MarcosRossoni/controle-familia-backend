package com.example.controlefamiliabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ControleFamiliaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleFamiliaBackendApplication.class, args);
	}

}
