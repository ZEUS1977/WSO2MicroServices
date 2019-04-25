package com.wso2.microservices.login.utils;

public class UserNotLoggedException extends Exception {

    public UserNotLoggedException(String errorMessage){
        super(errorMessage);
    }
}
