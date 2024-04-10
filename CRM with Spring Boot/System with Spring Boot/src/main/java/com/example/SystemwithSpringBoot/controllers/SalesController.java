package com.example.SystemwithSpringBoot.controllers;

import com.example.SystemwithSpringBoot.entity.Customer;
import com.example.SystemwithSpringBoot.entity.Sales;
import com.example.SystemwithSpringBoot.service.SalesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sales")
public class SalesController {
    public SalesService salesService;

    public SalesController(SalesService sService){
        salesService = sService;
    }

    @GetMapping("/get")
    public String getSales(Model model){
        List<Sales> sales = salesService.findAll();
        model.addAttribute("sales", sales);
        return "processInfo";
    }
}
