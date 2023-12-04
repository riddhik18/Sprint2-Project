package com.example.service.implementation;

import com.example.entity.Car;
import com.example.repository.CarRepository;
import com.example.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImplementation implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImplementation(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car updateCar(Long id, Car updatedCar) {
        Car existingCar = getCarById(id);

        if (existingCar != null) {
            existingCar.setBrand(updatedCar.getBrand());
            existingCar.setModel(updatedCar.getModel());
            existingCar.setColour(updatedCar.getColour());
            existingCar.setRegistrationNumber(updatedCar.getRegistrationNumber());
            existingCar.setAvailable(updatedCar.isAvailable());

            return saveCar(existingCar);
        }

        return null;
    }
}
