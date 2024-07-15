package com.example.SystemwithSpringBoot.dao;

import com.example.SystemwithSpringBoot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public List<Customer> findAll();

}

