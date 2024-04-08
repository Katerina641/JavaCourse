package com.example.restapiserviceex10JPA.service;

import com.example.restapiserviceex10JPA.dao.CarRepocitory;
import com.example.restapiserviceex10JPA.entity.Car;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServiceInterface{
    private CarRepocitory carRepocitory;

    @Autowired
    public CarService(CarRepocitory cDAO){
        carRepocitory = cDAO;
    }

    @Override
    public List<Car> findAll() {
        return carRepocitory.findAll();

    }

    @Override
    public Car findById(int id) {
        Optional<Car> result = carRepocitory.findById(id);
        Car car =null;
        if(result.isPresent()){
            car=result.get();
        }else{
            throw new RuntimeException("Did not find car id: " + id);
        }
        return car;
    }
@Transactional
    @Override
    public Car update(Car car) {
        return carRepocitory.save(car);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        carRepocitory.deleteById(id);

    }
}
