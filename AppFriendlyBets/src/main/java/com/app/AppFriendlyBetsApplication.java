package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude  = {SecurityAutoConfiguration.class})//Levantando la seguridad, por que si no pide usuario y clave para levantar el servicio
public class AppFriendlyBetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppFriendlyBetsApplication.class, args);
	}
}
