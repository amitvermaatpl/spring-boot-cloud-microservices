package com.inventory.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "inventory")
public class InventoryItem {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQ")
    @SequenceGenerator(sequenceName = "item_seq", allocationSize = 1, name = "ITEM_SEQ")
    private Long id;
    @Column(name = "product_code", nullable = false, unique = true)
    private String productCode;
    
    @Column(name = "quantity")
    private Integer availableQuantity = 0;
}
