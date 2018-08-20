package com.wso2.microservices.catalog.services;

import java.util.List;

public interface AccountService{
	
	List<Integer> getAllAccountsPerUser(String id);

}
