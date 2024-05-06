package com.example.management_service.modules.car.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.management_service.modules.car.dto.car_part.CarPartResponseDto;
import com.example.management_service.modules.car.dto.car_part.CreateCarPartRequestDto;
import com.example.management_service.modules.car.dto.car_part.UpdateCarPartRequestDto;
import com.example.management_service.modules.car.entities.CarPart;
import com.example.management_service.modules.car.mappers.CarPartMapper;
import com.example.management_service.modules.car.services.car_part.ICarPartService;

@RestController
@RequestMapping("/car-parts")
public class CarPartController {
    @Autowired
    private ICarPartService carPartService;

    @Autowired
    private CarPartMapper carPartMapper;

    @GetMapping
    public ResponseEntity<List<CarPartResponseDto>> getAllCarParts() {
        List<CarPart> carParts = carPartService.getAllCarParts();
        return ResponseEntity.ok(carPartMapper.toDto(carParts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarPartResponseDto> getCarPartById(@PathVariable Long id) {
        CarPart carPart = carPartService.getCarPartById(id);
        return ResponseEntity.ok(carPartMapper.toDto(carPart));
    }

    @PostMapping
    public ResponseEntity<CarPartResponseDto> createCarPart(
            @Validated @RequestBody CreateCarPartRequestDto requestDto) {
        CarPart carPart = carPartService.createCarPart(carPartMapper.toEntity(requestDto));
        return new ResponseEntity<>(carPartMapper.toDto(carPart), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarPartResponseDto> updateCarPart(@PathVariable Long id,
            @Validated @RequestBody UpdateCarPartRequestDto requestDto) {
        CarPart updatedCarPart = carPartService.updateCarPart(id, carPartMapper.toEntity(requestDto));
        return ResponseEntity.ok(carPartMapper.toDto(updatedCarPart));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarPart(@PathVariable Long id) {
        carPartService.deleteCarPart(id);
        return ResponseEntity.noContent().build();
    }
}