package com.wso2.microservices.catalog;

import org.jasypt.util.text.BasicTextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.wso2.microservices.catalog.daos.ProductDao;
import com.wso2.microservices.catalog.entities.Product;

@SpringBootApplication
public class Catalog implements CommandLineRunner{

	@Autowired
	ProductDao productDao;

	private static final Logger log = LoggerFactory.getLogger(Catalog.class);


	public static void main(String[] args){
		SpringApplication.run(Catalog.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		//...
		log.info("Hello 1");



		productDao.save(new Product(1, "Product1", "Product Number 1",  "A", 111.00));
		productDao.save(new Product(2, "Product2", "Product Number 2",  "A",222.00));
		productDao.save(new Product(3, "Product3", "Product Number 3",  "A",333.00));
		productDao.save(new Product(4, "Product4", "Product Number 4",  "B",444.00));
		productDao.save(new Product(5, "Product5", "Product Number 5",  "B",555.00));
		productDao.save(new Product(6, "Product6", "Product Number 6",  "B",666.00));
		productDao.save(new Product(7, "Product7", "Product Number 7",  "C",777.00));
		productDao.save(new Product(8, "Product8", "Product Number 8",  "C",888.00));
		productDao.save(new Product(9, "Product9", "Product Number 9",  "C",999.00));
		productDao.save(new Product(10, "Product10", "Product Number 10",  "D",1010.00));
		productDao.save(new Product(11, "Product11", "Product Number 11",  "D",1111.00));
		productDao.save(new Product(12, "Product12", "Product Number 12",  "D",1212.00));
		productDao.save(new Product(13, "Product13", "Product Number 13",  "E",1313.00));
		productDao.save(new Product(14, "Product14", "Product Number 14",  "E",1313.00));

	}

	@Bean
	public BasicTextEncryptor textEncryptor(){
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("mySecretEncriptionKeyBlaBla1234");
		return textEncryptor;
	}



}
