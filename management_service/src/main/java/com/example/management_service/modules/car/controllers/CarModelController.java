package com.example.management_service.modules.car.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.example.management_service.modules.car.dto.car_model.CarModelResponseDto;
import com.example.management_service.modules.car.dto.car_model.CreateCarModelRequestDto;
import com.example.management_service.modules.car.dto.car_model.UpdateCarModelRequestDto;
import com.example.management_service.modules.car.entities.CarModel;
import com.example.management_service.modules.car.mappers.CarModelMapper;
import com.example.management_service.modules.car.services.car_brand.ICarBrandService;
import com.example.management_service.modules.car.services.car_model.ICarModelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/car-models")
public class CarModelController {
    @Autowired
    private ICarModelService carModelService;
    @Autowired
    private CarModelMapper carModelMapper;
    @Autowired
    private ICarBrandService carBrandService;

    @GetMapping
    public ResponseEntity<List<CarModelResponseDto>> getAllCarModels() {
        List<CarModel> carModels = carModelService.getAllCarModels();
        return ResponseEntity.ok(carModelMapper.toDto(carModels));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarModelResponseDto> getCarModelById(@PathVariable Long id) {
        CarModel carModel = carModelService.getCarModelById(id);
        return ResponseEntity.ok(carModelMapper.toDto(carModel));
    }

    @PostMapping
    public ResponseEntity<CarModelResponseDto> createCarModel(
            @Valid @RequestBody CreateCarModelRequestDto createCarModelRequestDto) {
        CarModel carModel = carModelService.createCarModel(carModelMapper.toEntity(createCarModelRequestDto));
        carBrandService.getCarBrandById(carModel.getCarBrand().getId());
        return new ResponseEntity<>(carModelMapper.toDto(carModel), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarModelResponseDto> updateCarModel(@PathVariable Long id,
            @Valid @RequestBody UpdateCarModelRequestDto carModelRequestDto) {
        CarModel carModel = carModelMapper.toEntity(carModelRequestDto, id);

        CarModel updatedCarModel = carModelService.updateCarModel(carModel);
        return ResponseEntity.ok(carModelMapper.toDto(updatedCarModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarModel(@PathVariable Long id) {
        carModelService.deleteCarModel(id);
        return ResponseEntity.noContent().build();
    }
}
