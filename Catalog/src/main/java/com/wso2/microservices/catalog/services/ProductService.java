package com.wso2.microservices.catalog.services;

import java.util.List;

import com.wso2.microservices.catalog.entities.Product;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Integer id);
	List<Product> getProductsByCategory(String category);
}
