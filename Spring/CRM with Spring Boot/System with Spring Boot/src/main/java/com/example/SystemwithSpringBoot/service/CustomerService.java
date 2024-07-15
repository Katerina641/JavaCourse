package com.example.SystemwithSpringBoot.service;

import com.example.SystemwithSpringBoot.dao.CustomerRepository;
import com.example.SystemwithSpringBoot.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInterface{

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository cRepository){
        customerRepository=cRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        Optional<Customer> result = customerRepository.findById(id);
        Customer customer = null;

        if(result.isPresent()){
            customer = result.get();
        } else {
            throw new RuntimeException("Couldn't find customer id: " + id);
        }

        return customer;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(int id) {
        customerRepository.deleteById(id);

    }
}
