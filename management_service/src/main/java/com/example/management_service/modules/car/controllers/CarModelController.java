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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/car-models")
@Tag(name = "Car Model Management", description = "APIs to manage car models")
public class CarModelController {
    @Autowired
    private ICarModelService carModelService;

    @Autowired
    private CarModelMapper carModelMapper;

    @Autowired
    private ICarBrandService carBrandService;

    @Operation(summary = "Get all car models", description = "Retrieve a list of all car models.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<CarModelResponseDto>> getAllCarModels() {
        List<CarModel> carModels = carModelService.getAllCarModels();
        return ResponseEntity.ok(carModelMapper.toDto(carModels));
    }

    @Operation(summary = "Get car model by ID", description = "Retrieve a car model by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved car model", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarModelResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Car model not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarModelResponseDto> getCarModelById(@PathVariable Long id) {
        CarModel carModel = carModelService.getCarModelById(id);
        return ResponseEntity.ok(carModelMapper.toDto(carModel));
    }

    @Operation(summary = "Create a new car model", description = "Create a new car model with the given details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car model created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarModelResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CarModelResponseDto> createCarModel(
            @Valid @RequestBody CreateCarModelRequestDto createCarModelRequestDto) {
        CarModel carModel = carModelService.createCarModel(carModelMapper.toEntity(createCarModelRequestDto));
        carBrandService.getCarBrandById(carModel.getCarBrand().getId());
        return new ResponseEntity<>(carModelMapper.toDto(carModel), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a car model", description = "Update the details of an existing car model.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car model updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarModelResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Car model not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CarModelResponseDto> updateCarModel(@PathVariable Long id,
            @Valid @RequestBody UpdateCarModelRequestDto carModelRequestDto) {
        CarModel carModel = carModelMapper.toEntity(carModelRequestDto, id);

        CarModel updatedCarModel = carModelService.updateCarModel(id, carModel);
        return ResponseEntity.ok(carModelMapper.toDto(updatedCarModel));
    }

    @Operation(summary = "Delete a car model", description = "Delete a car model by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Car model deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Car model not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarModel(@PathVariable Long id) {
        carModelService.deleteCarModel(id);
        return ResponseEntity.noContent().build();
    }
}
