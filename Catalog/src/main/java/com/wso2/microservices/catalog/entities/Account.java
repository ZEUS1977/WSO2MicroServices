package com.wso2.microservices.catalog.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	
    public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(Integer id, User fkUser) {
		super();
		this.id = id;
		this.fkUser = fkUser;
	}
    //String ID, String FK_USER, Double TOTAL
    @Id @GeneratedValue
    @Column(name="ID")
    private Integer id;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getFkUser() {
		return fkUser;
	}

	public void setFkUser(User fkUser) {
		this.fkUser = fkUser;
	}
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OWNER")
    private User fkUser;
}
