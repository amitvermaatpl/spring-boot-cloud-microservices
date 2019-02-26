package com.cloud.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class CloudHystrixServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudHystrixServerApplication.class, args);
	}

}

