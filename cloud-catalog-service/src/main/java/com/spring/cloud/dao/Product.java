package com.spring.cloud.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROD_SEQ")
    @SequenceGenerator(sequenceName = "product_seq", allocationSize = 1, name = "PROD_SEQ")
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String code;
    
    @Column(nullable = false)
    private String name;
    
    private String description;  
    
    private double price;
    
    @Transient
    private boolean inStock = true;
}
