package com.Lab08;

import controller.CustomErrorController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "controller")
public class Lab08Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab08Application.class, args);
	}

}
