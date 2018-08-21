package com.wso2.microservices.catalog.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wso2.microservices.catalog.entities.Account;

public interface AccountDao extends JpaRepository<Account, Integer>{

    @Query(value = "SELECT * FROM accounts WHERE USER=:user", nativeQuery = true)
    List<Account> getAllAccountsPerUser(@Param("user") Integer user);

    List<Account> findByFkUser(Integer fkUser);
}
