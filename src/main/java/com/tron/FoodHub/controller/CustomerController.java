package com.tron.FoodHub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tron.FoodHub.entity.Customer;
import com.tron.FoodHub.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") long customerId) {
        Customer customer = customerService.getCustomerById(customerId);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(customer);
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable(value = "name") String name) {
        Customer customer = customerService.getCustomerByName(name);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(customer);
    }


    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
    	//customer.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        return customerService.createCustomer(customer);
    }
    
    @PostMapping("/name")
    public ResponseEntity<Customer> createOrUpdateCustomerByName(@RequestBody Customer customer) {
        // Try to find an existing customer by name
        Customer existingCustomer = customerService.getCustomerByName(customer.getCustomerFullName());
        if (existingCustomer != null) {
            // Update existing customer
            existingCustomer.setCustomerFullName(customer.getCustomerFullName());
            existingCustomer.setMobile(customer.getMobile());
            // Other fields as necessary
            Customer updatedCustomer = customerService.updateCustomer(existingCustomer.getCustomerId(), existingCustomer);
            return ResponseEntity.ok().body(updatedCustomer);
        } else {
            // Create new customer
            Customer newCustomer = customerService.createCustomer(customer);
            return ResponseEntity.ok().body(newCustomer);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") long customerId, @RequestBody Customer customerDetails) {
        Customer updatedCustomer = customerService.updateCustomer(customerId, customerDetails);

        if (updatedCustomer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable(value = "id") long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}
