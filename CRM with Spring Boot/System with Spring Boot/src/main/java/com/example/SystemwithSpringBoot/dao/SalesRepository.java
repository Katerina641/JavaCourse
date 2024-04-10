package com.example.SystemwithSpringBoot.dao;

import com.example.SystemwithSpringBoot.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Integer> {

    public List<Sales> findAll();

}
