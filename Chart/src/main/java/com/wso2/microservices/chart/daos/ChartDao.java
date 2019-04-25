package com.wso2.microservices.chart.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wso2.microservices.chart.entities.Chart;

public interface ChartDao extends JpaRepository<Chart,Integer>{

	 Optional<Chart> findById(Integer id);
	 
}
