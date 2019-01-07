package com.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
 * To enable Circuit Breaker add @EnableCircuitBreaker annotation on catalog-service entry-point class
 */

/*Without this: Parameter 1 of constructor in com.catalog.service.InventoryServiceClient required a 
bean of type 'com.catalog.service.InventoryServiceFeignClient' that could not be found.*/
@EnableFeignClients 
@EnableCircuitBreaker
@EnableHystrixDashboard
@SpringBootApplication
public class SpringCloudCatalogServiceApplication {
	
	/*
	 * Eureka service registry: if more that 2 components of a service is running
	 */
	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudCatalogServiceApplication.class, args);
	}

}

