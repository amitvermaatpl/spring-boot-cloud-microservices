package com.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringCloudInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudInventoryServiceApplication.class, args);
	}

}

