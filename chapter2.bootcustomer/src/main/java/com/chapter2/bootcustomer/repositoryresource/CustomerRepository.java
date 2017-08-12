package com.chapter2.bootcustomer.repositoryresource;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.chapter2.bootcustomer.entity.Customer;


//RepositoryRestResource annotation u restfull servisten repository e erişime imkan tanır
@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer,Long>{

	Optional<Customer> findByName(@Param("name") String name);
}
