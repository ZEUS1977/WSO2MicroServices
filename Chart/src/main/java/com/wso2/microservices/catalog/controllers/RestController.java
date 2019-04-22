package com.wso2.microservices.catalog.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wso2.microservices.catalog.services.ChartService;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    private static final Logger log = LoggerFactory.getLogger(RestController.class);
    
    @Autowired
    ChartService accountService;

    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello everyone!";
    }

 


    /*---------------------------INNER CLASS------------------------*/

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

}