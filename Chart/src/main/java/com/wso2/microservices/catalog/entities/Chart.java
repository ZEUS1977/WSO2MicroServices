package com.wso2.microservices.catalog.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity                                  //JPA defines an Entity
@Table(name = "Chart")                   //JPA (if table name in the DB differs from Class Name)
public class Chart {
	
	    public Chart(Integer id, List<ProductId> products, Integer chart, Integer user) {
		super();
		this.id = id;
		this.products = products;
		this.chart = chart;
		this.user = user;
	}

		public Chart() {
		super();
		// TODO Auto-generated constructor stub
	}

		@Id @GeneratedValue                            //JPA id of the table
	    @Column(name="ID")                //JPA (if column name is different from variable name)
	    private Integer id;
	   
	    @OneToMany
	    private List<ProductId> products;
	    
	    @Column(name="CHART")  
	    private Integer chart;
	    
	    @Column(name="USER")  
	    private Integer user;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public List<ProductId> getProducts() {
			return products;
		}

		public void setProducts(List<ProductId> products) {
			this.products = products;
		}

		public Integer getChart() {
			return chart;
		}

		public void setChart(Integer chart) {
			this.chart = chart;
		}

		public Integer getUser() {
			return user;
		}

		public void setUser(Integer user) {
			this.user = user;
		}
	   

}
