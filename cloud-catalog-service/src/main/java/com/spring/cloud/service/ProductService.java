package com.spring.cloud.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spring.cloud.dao.Product;
import com.spring.cloud.dao.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	private final ServiceClient serviceClient;
	
	@Value("${inventory.service.name}")
	private String inventoryService;
	
	@Autowired
	public ProductService(ProductRepository repository, ServiceClient serviceClient) {
		this.productRepository=repository;
		this.serviceClient=serviceClient;
	}
	
	public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
 
	/*
	 * public Optional<Product> findProductByCode(String code) { return
	 * productRepository.findByCode(code); }
	 */
    
    public Optional<Product> findProductByCode(String code) {    	
    	log.info("Fetching product by code= "+code);
        Optional<Product> productOptional = productRepository.findByCode(code);
    	if(productOptional.isPresent()) {
    		log.info("Fetching inventory level for product_code: "+code);
    		
    		Optional<ProductInventoryResponse> itemResponseEntity =
                    this.serviceClient.getProductInventoryByCode(code);
    		
    		if(itemResponseEntity.isPresent()) {
                Integer quantity = itemResponseEntity.get().getAvailableQuantity();
                productOptional.get().setInStock(quantity > 0);
            }
    	}
        return productOptional;
    }
}

