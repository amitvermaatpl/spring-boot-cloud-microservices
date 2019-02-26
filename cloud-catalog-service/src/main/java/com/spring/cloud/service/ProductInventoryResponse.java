package com.spring.cloud.service;

import lombok.Data;

@Data
public class ProductInventoryResponse {
    private String productCode;
    private int availableQuantity;
}
