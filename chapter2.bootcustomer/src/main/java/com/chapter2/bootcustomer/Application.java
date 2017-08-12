package com.chapter2.bootcustomer;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.chapter2.bootcustomer.entity.Customer;
import com.chapter2.bootcustomer.repositoryresource.CustomerRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	//commandLineRunner bean olarak tanımladığı için startup da kayıtlar db ye insert edilecek
	@Bean
	CommandLineRunner init(CustomerRepository repo) {
		return(evt)->{
			repo.save(new Customer("Samet", "sametsafkan@gmail.com"));
			repo.save(new Customer("John","john@boot.com"));
			repo.save(new Customer("Smith","smith@boot.com"));
			repo.save(new Customer("Edgar","edgar@boot.com"));
			repo.save(new Customer("Martin","martin@boot.com"));
			repo.save(new Customer("Tom","tom@boot.com"));
			repo.save(new Customer("Sean","sean@boot.com"));
		};
	}
}