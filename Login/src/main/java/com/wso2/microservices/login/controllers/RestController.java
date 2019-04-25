package com.wso2.microservices.login.controllers;


import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wso2.microservices.login.controllers.RestController.JsonResponseBody;
import com.wso2.microservices.login.entities.User;
import com.wso2.microservices.login.services.AccountService;
import com.wso2.microservices.login.services.LoginService;
import com.wso2.microservices.login.utils.UserNotLoggedException;

import io.jsonwebtoken.ExpiredJwtException;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    private static final Logger log = LoggerFactory.getLogger(RestController.class);

    @Autowired
    LoginService loginService;
    
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
    public class JsonResponseBody{

		public JsonResponseBody() {
			super();
			// TODO Auto-generated constructor stub
		}

		public JsonResponseBody(int server, Object response) {
			super();
			this.server = server;
			this.response = response;
		}

		private int server;

        private Object response;
        
        public int getServer() {
			return server;
		}

		public void setServer(int server) {
			this.server = server;
		}

		public Object getResponse() {
			return response;
		}

		public void setResponse(Object response) {
			this.response = response;
		}

    }

    /*---------------------------------------------------------*/


    @CrossOrigin
    @RequestMapping(value = "/login", method = GET)
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

}