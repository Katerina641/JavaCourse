package com.example.SystemwithSpringBoot.service;

import com.example.SystemwithSpringBoot.entity.Customer;

import java.util.List;

public interface CustomerServiceInterface {

    List<Customer> findAll();
    Customer findById(int id);
    Customer save(Customer customer);
    void deleteById(int id);
}
