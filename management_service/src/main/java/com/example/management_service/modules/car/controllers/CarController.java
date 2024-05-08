package com.example.management_service.modules.car.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.management_service.modules.car.dto.car.CarResponseDto;
import com.example.management_service.modules.car.dto.car.CreateCarRequestDto;
import com.example.management_service.modules.car.dto.car.UpdateCarRequestDto;
import com.example.management_service.modules.car.entities.Car;
import com.example.management_service.modules.car.mappers.CarMapper;
import com.example.management_service.modules.car.services.car.ICarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ICarService carService;

    @GetMapping
    public ResponseEntity<?> getCars(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(carService.getCars(pageRequest));
    }

    @GetMapping("/{carId}")
    public ResponseEntity<?> getCarById(@PathVariable Long carId) {
        Car car = carService.getCarById(carId);
        CarResponseDto carDTO = carMapper.toDto(car);
        return ResponseEntity.ok(carDTO);
    }

    @PostMapping
    public ResponseEntity<?> createCar(@Valid @RequestBody CreateCarRequestDto carDTO) {
        Car car = carMapper.toEntity(carDTO);
        car = carService.createCar(car);
        CarResponseDto createdCarDTO = carMapper.toDto(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCarDTO);
    }

    @PutMapping("/{carId}")
    public ResponseEntity<?> updateCar(@PathVariable Long carId, @Valid @RequestBody UpdateCarRequestDto carDTO) {
        Car car = carMapper.toEntity(carDTO, carId);
        CarResponseDto updatedCarDTO = carMapper.toDto(car);
        return ResponseEntity.ok(updatedCarDTO);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Long carId) {
        carService.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }
}
