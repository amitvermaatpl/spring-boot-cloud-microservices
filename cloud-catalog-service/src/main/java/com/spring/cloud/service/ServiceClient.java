package com.spring.cloud.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class ServiceClient {
	private final RestTemplate restTemplate;
	
	@Value("${inventory.service.name}")
	private String inventoryService;
	
	public ServiceClient(RestTemplate restTemplate) {
		this.restTemplate= restTemplate;
	}
	
	/*
     * USE @HystrixCommand annotation on any method we want to apply timeout and fallback method. So that if it doesn’t 
     * receive the response within the certain time limit the call gets timed out and invoke the configured fallback method. 
     * The fallback method should be defined in the same class and should have the same signature.
     */
	@HystrixCommand(commandKey = "inventory-by-productcode", fallbackMethod = "getDefaultProductInventoryByCode")
    public Optional<ProductInventoryResponse> getProductInventoryByCode(String productCode)
    {
        ResponseEntity<ProductInventoryResponse> itemResponseEntity =
                restTemplate.getForEntity(inventoryService, ProductInventoryResponse.class, productCode);
        
        /*
        // Simulate Delay: To test Circuit Breaker fallback method //
        try {
            java.util.concurrent.TimeUnit.SECONDS.sleep(5);  // In app.properties we configured timeout in 2secs.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        
        if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
            return Optional.ofNullable(itemResponseEntity.getBody());
        } else {
            log.error("Unable to get inventory level for product_code: " + productCode + ", StatusCode: " + itemResponseEntity.getStatusCode());
            return Optional.empty();
        }
    }
	
	@SuppressWarnings("unused")
    Optional<ProductInventoryResponse> getDefaultProductInventoryByCode(String productCode) {
        log.info("Returning default ProductInventoryByCode for productCode: "+productCode);
        ProductInventoryResponse response = new ProductInventoryResponse();
        response.setProductCode(productCode);
        response.setAvailableQuantity(50);
        return Optional.ofNullable(response);
    }

}
