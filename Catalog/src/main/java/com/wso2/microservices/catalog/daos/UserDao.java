package com.wso2.microservices.catalog.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wso2.microservices.catalog.entities.User;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, String>{
    //custom
    Optional<User> findById(Integer id);
}
