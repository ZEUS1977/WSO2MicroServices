package com.wso2.microservices.catalog.controllers;


import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wso2.microservices.catalog.entities.Product;
import com.wso2.microservices.catalog.services.ProductService;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    private static final Logger log = LoggerFactory.getLogger(RestController.class);


    @Autowired
    ProductService productService;


    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello everyone!";
    }

 


    

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
    
    @RequestMapping(value = "/product/{id}", method = GET)
    public ResponseEntity<JsonResponseBody> findProductById(@PathVariable Integer id){
    	
    	Product product  = productService.getProductById(id);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), product));
    }
    
    
}