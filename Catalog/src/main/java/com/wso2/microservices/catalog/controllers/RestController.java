package com.wso2.microservices.catalog.controllers;


import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import com.wso2.microservices.catalog.entities.Product;
import com.wso2.microservices.catalog.entities.User;
import com.wso2.microservices.catalog.services.AccountService;
import com.wso2.microservices.catalog.services.LoginService;
import com.wso2.microservices.catalog.services.ProductService;
import com.wso2.microservices.catalog.utils.UserNotLoggedException;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    private static final Logger log = LoggerFactory.getLogger(RestController.class);

    @Autowired
    LoginService loginService;

    @Autowired
    ProductService productService;
    
    @Autowired
    AccountService accountService;



    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello everyone!";
    }

 


    /*---------------------------INNER CLASS------------------------*/
    //Spring Validator Example
    private class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.equals(clazz);
        }

        @Override
        public void validate(Object obj, Errors errors) {
            User user = (User) obj;
            if (user.getPassword().length() < 8) {
                errors.rejectValue("password", "the password must be at least 8 chars long!");
            }
        }
    }
    /*---------------------------------------------------------*/

    /**
     * inner class used as the Object tied into the Body of the ResponseEntity.
     * It's important to have this Object because it is composed of server response code and response object.
     * Then, JACKSON LIBRARY automatically convert this JsonResponseBody Object into a JSON response.
     */
    @AllArgsConstructor
    public class JsonResponseBody{
        @Getter @Setter
        private int server;
        @Getter @Setter
        private Object response;
    }

    /*---------------------------------------------------------*/


    @CrossOrigin
    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<JsonResponseBody> loginUser(@RequestParam(value ="id") Integer id, @RequestParam(value="password") String pwd){
        //check if user exists in DB -> if exists generate JWT and send back to client
        try {
            Optional<User> userr = loginService.getUserFromDbAndVerifyPassword(id, pwd);
            if(userr.isPresent()){
                User user = userr.get();
                String jwt = loginService.createJwt(user.getId(), user.getUser(), user.getType(), new Date());
                return ResponseEntity.status(HttpStatus.OK).header("jwt", jwt).body(new JsonResponseBody(HttpStatus.OK.value(), "Success! User logged in!"));
            }
        }catch (UserNotLoggedException e1){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Login failed! Wrong credentials" + e1.toString()));
        }catch (UnsupportedEncodingException e2){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Token Error" + e2.toString()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "No corrispondence in the database of users"));
    }

    @RequestMapping(value = "/accounts/user", method = POST)
    public ResponseEntity<JsonResponseBody> fetchAllAccountsPerUser(HttpServletRequest request){
        //request -> fetch JWT -> recover User Data -> Get user accounts from DB
        try {
            Map<String, Object> userData = loginService.verifyJwtAndGetData(request);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), accountService.getAllAccountsPerUser((String) userData.get("id"))));
        }catch(UnsupportedEncodingException e1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Bad Request: " + e1.toString()));
        }catch(UserNotLoggedException e2){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "User not logged! Login first : " + e2.toString()));
        }catch(ExpiredJwtException e3){
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new JsonResponseBody(HttpStatus.GATEWAY_TIMEOUT.value(), "Session Expired!: " + e3.toString()));
        }
    }
    
    @RequestMapping(value = "/products/all", method = GET)
    public ResponseEntity<JsonResponseBody> findAllProducts(){
    	
    	List<Product> products  = productService.getAllProducts();
    	
    	return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), products));
    }
    
    @RequestMapping(value = "/products/category/{category}", method = GET)
    public ResponseEntity<JsonResponseBody> findAllProductsByCategory(@PathVariable String category){
    	
    	List<Product> products  = productService.getProductsByCategory(category);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), products));
    }
    
    
}