package com.wso2.microservices.login;

import org.jasypt.util.text.BasicTextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.wso2.microservices.login.daos.AccountDao;
import com.wso2.microservices.login.daos.UserDao;
import com.wso2.microservices.login.entities.Account;
import com.wso2.microservices.login.entities.User;
import com.wso2.microservices.login.utils.EncryptionUtils;

@SpringBootApplication
public class Login implements CommandLineRunner{

	@Autowired
	UserDao userDao;

	@Autowired
	AccountDao accountDao;

	@Autowired
	EncryptionUtils encryptionUtils;

	private static final Logger log = LoggerFactory.getLogger(Login.class);


	public static void main(String[] args){
		SpringApplication.run(Login.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		//...
		log.info("Hello 1");

		String encryptedPwd = encryptionUtils.encrypt("wso2");
		System.out.println("Ecripted pwd into DB: " + encryptedPwd);
		log.info("Ecripted pwd into DB: " + encryptedPwd);
		
		User user1 = new User(1,"RGNLSN87H13D761R", "Alessandro Argentieri", encryptedPwd, "user",null);
		userDao.save(user1);
		encryptedPwd = encryptionUtils.encrypt("wso2");
		
		User user2 = new User(2,"FRNFBA85M08D761M", "Fabio Fiorenza", encryptedPwd, "user", null);
		userDao.save(user2);

		encryptedPwd = encryptionUtils.encrypt("wso2");
		
		User user3 = new User(3,"DSTLCU89R52D761R", "Lucia Distante", encryptedPwd, "user", null);
		userDao.save(user3);

		accountDao.save(new Account(1, user1));
		accountDao.save(new Account(2, user1));
		accountDao.save(new Account(3, user2));
		accountDao.save(new Account(4, user3));
		accountDao.save(new Account(5, user3));


	}

	@Bean
	public BasicTextEncryptor textEncryptor(){
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("mySecretEncriptionKeyBlaBla1234");
		return textEncryptor;
	}



}
