package com.employee.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EmployeeRecordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeRecordsApplication.class, args);
	}

}
