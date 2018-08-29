package com.wso2.microservices.catalog.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="account")
@AllArgsConstructor @NoArgsConstructor
public class Account {

    //String ID, String FK_USER, Double TOTAL
    @Id @GeneratedValue
    @Column(name="ID")
    @Getter @Setter
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OWNER")
    private User fkUser;
}
