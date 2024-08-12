package com.tron.FoodHub.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tron.FoodHub.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Customer findByCustomerFullName(String customerFullName);
}
