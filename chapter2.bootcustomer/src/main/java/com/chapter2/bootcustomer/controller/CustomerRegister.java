package com.chapter2.bootcustomer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.chapter2.bootcustomer.entity.Customer;
import com.chapter2.bootcustomer.repositoryresource.CustomerRepository;

@Component
@Lazy
public class CustomerRegister {

	CustomerRepository repository;
	Sender sender;
	
	@Autowired
	CustomerRegister(CustomerRepository repository, Sender sender){
		this.repository = repository;
		this.sender = sender;
	}
	
	Customer register(Customer customer) {
		Optional <Customer> existingCustomer = repository.findByName(customer.getName());
		if(existingCustomer.isPresent()) {
			throw new RuntimeException("it is already exist");
		}else {
			repository.save(customer);
			sender.send(customer.getEmail());
		}
		return customer;
	}
}
