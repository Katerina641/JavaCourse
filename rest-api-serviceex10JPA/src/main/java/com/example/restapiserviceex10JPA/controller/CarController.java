package com.example.restapiserviceex10JPA.controller;

import com.example.restapiserviceex10JPA.entity.Car;
import com.example.restapiserviceex10JPA.service.CarServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {
    private CarServiceInterface carServiceInterface;

    public CarController(CarServiceInterface cService){
        carServiceInterface = cService;
    }

    @GetMapping("/cars")
    public List<Car> findAll(){
        return carServiceInterface.findAll();
    }

    @GetMapping("/cars/{carId}")
    public Car getCar(@PathVariable int carId){
        Car car =carServiceInterface.findById(carId);
        if(car == null){
            throw new RuntimeException("car is not found" + carId);
        }
        return car;
    }

    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car){
        car.setId(0);
        Car carDB = carServiceInterface.update(car);
        return carDB;
    }

    @DeleteMapping("/cars/{carId}")
    public String deleteCar(@PathVariable int carId){
        Car car = carServiceInterface.findById(carId);
        if(car == null){
            throw new RuntimeException("car is not found" + carId);
        }
        carServiceInterface.deleteById(carId);
        return "Deleted car with id: " + carId;

    }
}
