package com.ptit.mobie.taskfamily_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TaskfamilyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskfamilyServiceApplication.class, args);
	}

}
