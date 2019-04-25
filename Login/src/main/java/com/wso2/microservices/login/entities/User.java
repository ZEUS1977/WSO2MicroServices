package com.wso2.microservices.login.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//1) JPA
//2) JSR-303
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity                                  //JPA defines an Entity
@Table(name = "users")                   //JPA (if table name in the DB differs from Class Name)
public class User {

    public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String fiscalCode, String user, String password, String type, List<Account> accounts) {
		super();
		this.id = id;
		this.fiscalCode = fiscalCode;
		this.user = user;
		this.password = password;
		this.type = type;
		this.accounts = accounts;
	}

    //String ID, String USERNAME, String PASSWORD, String PERMISSION

    @Id @GeneratedValue                            //JPA id of the table
    @Column(name="ID")                //JPA (if column name is different from variable name)
    private Integer id;
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="FISCALCODE")          //JPA (if column name is different from variable name)
    @NotEmpty @NotBlank @NotNull      //JSR-303 Validation
    private String fiscalCode;


    @Column(name="USER")          //JPA (if column name is different from variable name)
    @NotEmpty @NotBlank @NotNull      //JSR-303 Validation
    private String user;

    @Column(name="PASSWORD")          //JPA (if column name is different from variable name)
    @NotEmpty @NotBlank @NotNull      //JSR-303 Validation   
    private String password;

    @Column(name="TYPE")        	  //JPA (if column name is different from variable name)
    @NotEmpty @NotBlank @NotNull      //JSR-303 Validation
    private String type;
    
    @OneToMany
    private List<Account> accounts;

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}



}
