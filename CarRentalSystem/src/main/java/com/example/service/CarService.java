package com.example.service;

import com.example.entity.Car;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CarService {

    List<Car> getAllCars();

    Car getCarById(Long id);

    Car saveCar(Car car);

    void deleteCar(Long id);

    Car updateCar(Long id, Car updatedCar);
}
