package com.example.management_service.modules.car.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.management_service.modules.car.dto.car_brand.CarBrandResponseDto;
import com.example.management_service.modules.car.dto.car_brand.CreateCarBrandRequestDto;
import com.example.management_service.modules.car.dto.car_brand.UpdateCarBrandRequestDto;
import com.example.management_service.modules.car.entities.CarBrand;
import com.example.management_service.modules.car.mappers.CarBrandMapper;
import com.example.management_service.modules.car.services.car_brand.ICarBrandService;

@RestController
@RequestMapping("/car-brands")
public class CarBrandController {
    @Autowired
    private ICarBrandService carBrandService;
    @Autowired
    private CarBrandMapper carBrandMapper;

    @GetMapping
    public ResponseEntity<List<CarBrandResponseDto>> getAllCarBrands() {
        List<CarBrand> carBrands = carBrandService.getAllCarBrands();
        return ResponseEntity.ok(carBrands.stream().map(carBrandMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarBrandResponseDto> getCarBrandById(@PathVariable Long id) {
        CarBrand carBrand = carBrandService.getCarBrandById(id);
        return ResponseEntity.ok(carBrandMapper.toDto(carBrand));
    }

    @PostMapping
    public ResponseEntity<CarBrandResponseDto> createCarBrand(
            @Validated @RequestBody CreateCarBrandRequestDto createCarBrandRequestDto) {
        CarBrand carBrand = carBrandService.createCarBrand(carBrandMapper.createDtoToModel(createCarBrandRequestDto));
        return new ResponseEntity<>(carBrandMapper.toDto(carBrand), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarBrandResponseDto> updateCarBrand(@PathVariable Long id,
            @Validated @RequestBody UpdateCarBrandRequestDto carBrandRequestDto) {
        CarBrand carBrand = carBrandMapper.updateDtoToModel(carBrandRequestDto, id);

        CarBrand updatedCarBrand = carBrandService.updateCarBrand(carBrand);
        return ResponseEntity.ok(carBrandMapper.toDto(updatedCarBrand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarBrand(@PathVariable Long id) {
        carBrandService.deleteCarBrand(id);
        return ResponseEntity.noContent().build();
    }
}