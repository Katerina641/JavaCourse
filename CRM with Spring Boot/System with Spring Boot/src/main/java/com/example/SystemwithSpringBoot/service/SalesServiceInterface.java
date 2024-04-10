package com.example.SystemwithSpringBoot.service;

import com.example.SystemwithSpringBoot.entity.Sales;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.List;

public interface SalesServiceInterface {
    List<Sales> findAll();

}
