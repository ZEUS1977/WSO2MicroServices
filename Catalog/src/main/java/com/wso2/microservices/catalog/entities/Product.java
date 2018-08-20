package com.wso2.microservices.catalog.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor   //Lombok annotations
@Entity                                  //JPA defines an Entity
@Table(name = "PRODUCT")                   //JPA (if table name in the DB differs from Class Name)
public class Product {
	

    @Id                               //JPA id of the table
    @Column(name="ID")                //JPA (if column name is different from variable name)
    @Getter @Setter                   //Lombok annotations
    private Integer id;
                             
    @Column(name="NAME")                //JPA (if column name is different from variable name)
    @NotEmpty @NotBlank @NotNull      //JSR-303 Validation
    @Getter @Setter                   //Lombok annotations
    private String name;
    
    @Column(name="DESCRIPTION")                //JPA (if column name is different from variable name)
    @NotEmpty @NotBlank @NotNull      //JSR-303 Validation
    @Getter @Setter                   //Lombok annotations
    private String description;

    @Column(name="CATEGORY")                //JPA (if column name is different from variable name)
    @NotEmpty @NotBlank @NotNull      //JSR-303 Validation
    @Getter @Setter                   //Lombok annotations
    private String category;
    
    @Column(name="PRICE")
    @Getter @Setter
    @NotNull
    private Double price;
}
