package com.codeup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class CapstoneApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CapstoneApplication.class);
	}
}
