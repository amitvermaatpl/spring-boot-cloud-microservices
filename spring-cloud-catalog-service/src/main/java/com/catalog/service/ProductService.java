package com.catalog.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catalog.bean.ProductInventoryResponse;
import com.catalog.dao.Product;
import com.catalog.dao.ProductRepository;
import com.catalog.utils.MyThreadLocalsHolder;

import lombok.extern.slf4j.Slf4j;
 
@Service
@Transactional
@Slf4j
public class ProductService {
	private static final Logger logger = Logger.getLogger(ProductService.class.getName());
    private final ProductRepository productRepository;
    private final InventoryServiceClient inventoryServiceClient;
     
    @Autowired
    public ProductService(ProductRepository productRepository, InventoryServiceClient inventoryServiceClient) {
        this.productRepository = productRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }
  
    public List<Product> findAllProducts() {
    	logger.log(Level.INFO, "Fetching all the products.");
        return productRepository.findAll();
    }
 
    /*public Optional<Product> findProductByCode(String code) {
        return productRepository.findByCode(code);
    }*/
   
    public Optional<Product> findProductByCode(String code) {
    	logger.log(Level.INFO, "Fetching product by code= ", code);
        Optional<Product> productOptional = productRepository.findByCode(code);
        if (productOptional.isPresent()) {
            String correlationId = UUID.randomUUID().toString();
            MyThreadLocalsHolder.setCorrelationId(correlationId);
            log.info("Before CorrelationID: "+ MyThreadLocalsHolder.getCorrelationId());
            log.info("Fetching inventory level for product_code: " + code);
            Optional<ProductInventoryResponse> itemResponseEntity =
                    this.inventoryServiceClient.getProductInventoryByCode(code);
            if (itemResponseEntity.isPresent()) {
                Integer quantity = itemResponseEntity.get().getAvailableQuantity();
                productOptional.get().setInStock(quantity > 0);
            }
            log.info("After CorrelationID: "+ MyThreadLocalsHolder.getCorrelationId());
        }
        return productOptional;
    }
}
