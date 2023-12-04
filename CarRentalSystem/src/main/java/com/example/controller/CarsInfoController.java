package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.service.CarService;

import org.springframework.ui.Model;

@Controller
public class CarsInfoController {

    private CarService carService;

    public CarsInfoController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/carsinfo")
    public String showCarInfo(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "carsinfo";
    }
}
