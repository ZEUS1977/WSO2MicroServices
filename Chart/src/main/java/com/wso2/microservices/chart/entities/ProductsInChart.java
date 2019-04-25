package com.wso2.microservices.chart.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity                                  //JPA defines an Entity
@Table(name = "PRODUCTS_IN_CHART")
public class ProductsInChart {
	
    @EmbeddedId
	private ProductsInChartKey Key;
    
    @Column(name="QUANTITY")
	private Integer quantity;

}
