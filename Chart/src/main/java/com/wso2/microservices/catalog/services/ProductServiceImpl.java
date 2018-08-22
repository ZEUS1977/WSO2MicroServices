package com.wso2.microservices.catalog.services;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.wso2.microservices.catalog.daos.ProductDao;
import com.wso2.microservices.catalog.entities.Product;

@Service @Transactional
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductDao productDao;

	@Override
	public List<Product> getAllProducts() {
		
		return productDao.findAll();
	}

	@Override
	@Query(value = "SELECT * FROM PRODUCT WHERE CATEGORY =:category", nativeQuery = true)
	public List<Product> getProductsByCategory(String category) {
		
		return productDao.findAllProductByCategory(category);
	}

	@Override
	public Product getProductById(Integer id) {
		
		return productDao.findOne(id);
	}

}
