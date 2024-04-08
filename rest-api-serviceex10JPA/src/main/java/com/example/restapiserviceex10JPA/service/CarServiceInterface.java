package com.example.restapiserviceex10JPA.service;

import com.example.restapiserviceex10JPA.entity.Car;

import java.util.List;

public interface CarServiceInterface {

    List<Car> findAll();

    Car findById(int id);

    Car update(Car car);

    void deleteById(int id);
}
