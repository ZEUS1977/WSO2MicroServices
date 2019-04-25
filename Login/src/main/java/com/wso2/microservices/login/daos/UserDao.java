package com.wso2.microservices.login.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wso2.microservices.login.entities.User;

public interface UserDao extends JpaRepository<User, Integer>{
    //custom
    Optional<User> findById(Integer id);
}
