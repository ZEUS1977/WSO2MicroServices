package com.wso2.microservices.catalog.services;

import com.wso2.microservices.catalog.daos.UserDao;
import com.wso2.microservices.catalog.entities.User;
import com.wso2.microservices.catalog.utils.EncryptionUtils;
import com.wso2.microservices.catalog.utils.JwtUtils;
import com.wso2.microservices.catalog.utils.UserNotLoggedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{

    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    UserDao userDao;

    @Autowired
    EncryptionUtils encryptionUtils;



    @Override
    public Optional<User> getUserFromDbAndVerifyPassword(Integer id, String password) throws UserNotLoggedException{

        Optional<User> userr = userDao.findById(id);
        if(userr.isPresent()){
            User user = userr.get();
            if(encryptionUtils.decrypt(user.getPassword()).equals(password)){
                log.info("Username and Password verified");
            }else{
                log.info("Username verified. Password not");
                throw new UserNotLoggedException("User not correctly logged in");
            }
        }
        return userr;
    }


    @Override
    public String createJwt(Integer userId, String name, String permission, Date datenow) throws UnsupportedEncodingException{
        Date expDate = datenow;
        expDate.setTime(datenow.getTime() + (300*1000));
        log.info("JWT Creation. Expiration time: " + expDate.getTime());
        String token = JwtUtils.generateJwt(userId, expDate, name, permission);
        return token;
    }


    @Override
    public Map<String, Object> verifyJwtAndGetData(HttpServletRequest request) throws UserNotLoggedException, UnsupportedEncodingException{
        String jwt = JwtUtils.getJwtFromHttpRequest(request);
        if(jwt == null){
            throw new UserNotLoggedException("Authentication token not found in the request");
        }
        Map<String, Object> userData = JwtUtils.jwt2Map(jwt);
        return userData;
    }


}
