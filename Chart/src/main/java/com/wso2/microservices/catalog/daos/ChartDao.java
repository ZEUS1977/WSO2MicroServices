package com.wso2.microservices.catalog.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wso2.microservices.catalog.entities.Chart;
import com.wso2.microservices.catalog.entities.ProductId;

public interface ChartDao extends JpaRepository<Chart,Integer>{

	 Optional<Chart> findById(Integer id);
	 
}
