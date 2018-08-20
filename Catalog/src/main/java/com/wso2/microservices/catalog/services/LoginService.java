package com.wso2.microservices.catalog.services;

import com.wso2.microservices.catalog.entities.User;
import com.wso2.microservices.catalog.utils.UserNotLoggedException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public interface LoginService {

    Optional<User> getUserFromDbAndVerifyPassword(Integer id, String password)throws UserNotLoggedException;
    //-> userDao.findById(id), encryptionUtils.decrypt(pwd) -> UserNotLoggedException

    String createJwt(Integer userId, String name, String permission, Date date) throws UnsupportedEncodingException;
    //-> JwtUtils.generateJwt(...) 						 -> UnsupportedEncodingException


    Map<String, Object> verifyJwtAndGetData(HttpServletRequest request) throws UserNotLoggedException, UnsupportedEncodingException;
    //-> JwtUtils.getJwtFromHttpRequest(request)		-> UserNotLoggedException
    // 	  -> JwtUtils.jwt2Map(jwt)						-> UnsupportedEncodingException
    //												->  ExpiredJwtException


}
