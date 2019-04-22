package com.wso2.microservices.catalog.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity                                  //JPA defines an Entity
@Table(name = "ProductId")                   //JPA (if table name in the DB differs from Class Name)
public class ProductId {

	    public ProductId(Integer id, Chart chart) {
		super();
		this.id = id;
		this.chart = chart;
	}

		public ProductId() {
		super();
		// TODO Auto-generated constructor stub
	}

		@Id @GeneratedValue               //JPA id of the table
	    @Column(name="ID")                //JPA (if column name is different from variable name)
	    private Integer id;
	    
	    @Column(name="CHART")
		@ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="ID")
	    private Chart chart;
}
