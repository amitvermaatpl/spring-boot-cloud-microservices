package com.spring.cloud.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cloud.dao.Product;
import com.spring.cloud.dao.ProductRepository;

@Service
@Transactional
public class ProductService {
	
	private final ProductRepository productRepository;
	
	@Autowired
	public ProductService(ProductRepository repository) {
		this.productRepository=repository;
	}
	
	public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
 
    public Optional<Product> findProductByCode(String code) {
        return productRepository.findByCode(code);
    }
}
