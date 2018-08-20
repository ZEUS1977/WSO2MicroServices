package com.wso2.microservices.catalog.services;


import com.wso2.microservices.catalog.daos.AccountDao;
import com.wso2.microservices.catalog.daos.ProductDao;
import com.wso2.microservices.catalog.entities.Account;
import com.wso2.microservices.catalog.entities.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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
		// TODO Auto-generated method stub
		return productDao.findAllProductByCategory(category);
	}

	@Override
	public Product getProductById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
