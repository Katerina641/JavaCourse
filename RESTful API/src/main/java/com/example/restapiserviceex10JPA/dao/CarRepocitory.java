package com.example.restapiserviceex10JPA.dao;

import com.example.restapiserviceex10JPA.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepocitory extends JpaRepository<Car, Integer> {
}
