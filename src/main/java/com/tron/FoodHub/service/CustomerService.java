package com.tron.FoodHub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tron.FoodHub.entity.Customer;
import com.tron.FoodHub.repo.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    
    public Customer getCustomerByName(String name) {
        return customerRepository.findByCustomerFullName(name);
    }

    public Customer getCustomerById(long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(long customerId, Customer customerDetails) {
        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer != null) {
            customer.setCustomerFullName(customerDetails.getCustomerFullName());
            customer.setMobile(customerDetails.getMobile());
            customer.setCreatedOn(customerDetails.getCreatedOn());
            return customerRepository.save(customer);
        }
        return null;
    }

    public void deleteCustomer(long customerId) {
        customerRepository.deleteById(customerId);
    }
}
