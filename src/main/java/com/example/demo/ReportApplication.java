package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ReportApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ReportApplication.class).properties("spring.config.name=application,InputConfiguration").run(args);
	}
}
