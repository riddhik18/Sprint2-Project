package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Car;
import com.example.service.CarService;

@Controller
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        super();
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String listCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "cars";
    }

    @GetMapping("/cars/new")
    public String createCarForm(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "create_car";
    }

    @PostMapping("/cars")
    public String saveCar(@ModelAttribute("car") Car car) {
        System.out.println("Received car: " + car);
        carService.saveCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/cars/edit/{id}")
    public String editCarForm(@PathVariable Long id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        return "edit_car";
    }

    @PostMapping("/cars/{id}")
    public String updateCar(@PathVariable Long id,
            @ModelAttribute("car") Car car,
            Model model) {

        Car existingCar = carService.getCarById(id);
        existingCar.setId(id);
        existingCar.setBrand(car.getBrand());
        existingCar.setModel(car.getModel());
        existingCar.setColour(car.getColour());
        existingCar.setRegistrationNumber(car.getRegistrationNumber());
        existingCar.setAvailable(car.isAvailable());

        carService.updateCar(id, existingCar);
        return "redirect:/cars";
    }

    @GetMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }
}
