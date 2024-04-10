package com.example.SystemwithSpringBoot.service;

import com.example.SystemwithSpringBoot.dao.SalesRepository;
import com.example.SystemwithSpringBoot.entity.Sales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.List;
@Service
public class SalesService implements SalesServiceInterface{

    private SalesRepository salesRepository;

    @Autowired
    public SalesService(SalesRepository sRepository){
        salesRepository = sRepository;
    }

    @Override
    public List<Sales> findAll() {
        return salesRepository.findAll();
    }
}
