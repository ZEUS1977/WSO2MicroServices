package com.wso2.microservices.chart.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity                                  //JPA defines an Entity
@Table(name = "CHART")                   //JPA (if table name in the DB differs from Class Name)
public class Chart {
	
	    public Chart(Integer id, List<ProductsInChart> products, Integer chart, Integer accountId) {
		super();
		this.id = id;
		this.prodsChart = products;
		this.accountId = accountId;
	}

		public Chart() {
		super();
		// TODO Auto-generated constructor stub
	}

		@Id @GeneratedValue                            //JPA id of the table
	    @Column(name="ID")                //JPA (if column name is different from variable name)
	    private Integer id;
	   
	    @OneToMany
	    private List<ProductsInChart> prodsChart;
	    
	    @Column(name="ACCOUNT_ID")  
	    private Integer accountId;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public List<ProductsInChart> getProducts() {
			return prodsChart;
		}

		public void setProducts(List<ProductsInChart> prodsChart) {
			this.prodsChart = prodsChart;
		}

		public Integer getAccount() {
			return accountId;
		}

		public void setAccount(Integer accountId) {
			this.accountId = accountId;
		}
	   

}
