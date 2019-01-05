package com.catalog.dao;

import lombok.Data;
import javax.persistence.*;
 
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
