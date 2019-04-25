package com.wso2.microservices.login.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


@Entity                                  //JPA defines an Entity
@Table(name = "PRODUCT")                   //JPA (if table name in the DB differs from Class Name)
public class Product {
	

    public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public Product(Integer id, String name, String description, String category, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
	}
	@Id @GeneratedValue                              //JPA id of the table
    @Column(name="ID")                //JPA (if column name is different from variable name)
    private Integer id;
                             
    @Column(name="NAME")                //JPA (if column name is different from variable name)
    @NotEmpty @NotBlank @NotNull      //JSR-303 Validation
    private String name;
    
    @Column(name="DESCRIPTION")                //JPA (if column name is different from variable name)
    @NotEmpty @NotBlank @NotNull      //JSR-303 Validation
    private String description;

    @Column(name="CATEGORY")                //JPA (if column name is different from variable name)
    @NotEmpty @NotBlank @NotNull      //JSR-303 Validation
    private String category;
    
    @Column(name="PRICE")
    @NotNull
    private Double price;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
