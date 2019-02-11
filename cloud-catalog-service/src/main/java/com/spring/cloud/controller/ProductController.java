package com.spring.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cloud.dao.Product;
import com.spring.cloud.exception.ProductNotFoundException;
import com.spring.cloud.service.ProductService;

@RefreshScope
@RestController
public class ProductController {
	
	private final ProductService productService;
	
	@Value("${app.service-name}")
	private String serviceName;
	
	@Autowired
	public ProductController(ProductService service) {
		this.productService=service;
	}
	
	@GetMapping("")
	public List<Product> allProducts(){
		return productService.findAllProducts();
	}
	
	@GetMapping("/{code}")
	public Product productByCode(@PathVariable String code){
		return productService.findProductByCode(code)
			   .orElseThrow(() -> new ProductNotFoundException("Product with code ["+code+"] doesn't exist"));
		
	}
	
	@GetMapping("/service")
	public String getServiceName() {
		return "Service Name [" + this.serviceName + "]";
	}
	
	
}
