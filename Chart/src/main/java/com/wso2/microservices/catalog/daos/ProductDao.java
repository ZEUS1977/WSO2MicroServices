package com.wso2.microservices.catalog.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wso2.microservices.catalog.entities.Product;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer>{

    @Query(value = "SELECT * FROM PRODUCT WHERE CATEGORY=:category", nativeQuery = true)
    List<Product> findAllProductByCategory(@Param("category")String category);
}
