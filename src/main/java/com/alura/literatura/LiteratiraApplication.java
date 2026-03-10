package com.alura.literatura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LiteratiraApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiteratiraApplication.class, args);
	}

}
