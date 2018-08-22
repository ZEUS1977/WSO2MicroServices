package com.wso2.microservices.catalog.entities;


//1) JPA
//2) JSR-303
//3) LOMBOK



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @NoArgsConstructor   //Lombok annotations
@Entity                                  //JPA defines an Entity
@Table(name = "users")                   //JPA (if table name in the DB differs from Class Name)
public class User {


    //String ID, String USERNAME, String PASSWORD, String PERMISSION

    @Id                               //JPA id of the table
    @Column(name="ID")                //JPA (if column name is different from variable name)
    @Getter @Setter                   //Lombok annotations
    private Integer id;
    
    @Column(name="FISCALCODE")          //JPA (if column name is different from variable name)
    @NotEmpty @NotBlank @NotNull      //JSR-303 Validation
    @Getter @Setter                   //Lombok annotations
    private String fiscalCode;


    @Column(name="USER")          //JPA (if column name is different from variable name)
    @NotEmpty @NotBlank @NotNull      //JSR-303 Validation
    @Getter @Setter                   //Lombok annotations
    private String user;

    @Column(name="PASSWORD")          //JPA (if column name is different from variable name)
    @NotEmpty @NotBlank @NotNull      //JSR-303 Validation
    @Getter @Setter                   //Lombok annotations
    private String password;

    @Column(name="TYPE")        	  //JPA (if column name is different from variable name)
    @NotEmpty @NotBlank @NotNull      //JSR-303 Validation
    @Getter @Setter                   //Lombok annotations
    private String type;



}
