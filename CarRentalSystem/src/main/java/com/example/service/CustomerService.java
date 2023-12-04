package com.example.service;

import com.example.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer saveCustomer(Customer customer);
    void updateCustomer(Long id, Customer updatedCustomer);
    void deleteCustomer(Long id);
    void createCustomer(Customer customer);
}
