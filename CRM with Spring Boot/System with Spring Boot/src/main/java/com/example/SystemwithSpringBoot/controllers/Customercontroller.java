package com.example.SystemwithSpringBoot.controllers;


import com.example.SystemwithSpringBoot.entity.Customer;
import com.example.SystemwithSpringBoot.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class Customercontroller {

    private CustomerService customerService;

    public Customercontroller(CustomerService cService){
        customerService = cService;
    }

    @GetMapping("/get")
    public String getCustomers(Model model){
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "get-customers";
    }
}
