package com.anderscore.testcontainers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WekanSchedulerApplication {

	public static void main(String[] args){
		new SpringApplicationBuilder()
				.sources(WekanSchedulerApplication.class)
				.run(args);
	}
}