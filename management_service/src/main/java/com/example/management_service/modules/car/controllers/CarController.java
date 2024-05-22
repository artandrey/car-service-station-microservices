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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cars")
@Tag(name = "Car Management", description = "APIs to manage cars")
public class CarController {
    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ICarService carService;

    @Operation(summary = "Get all cars", description = "Retrieve a paginated list of cars.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> getCars(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(carService.getCars(pageRequest));
    }

    @Operation(summary = "Get car by ID", description = "Retrieve a car by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved car", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Car not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{carId}")
    public ResponseEntity<?> getCarById(@PathVariable Long carId) {
        Car car = carService.getCarById(carId);
        CarResponseDto carDTO = carMapper.toDto(car);
        return ResponseEntity.ok(carDTO);
    }

    @Operation(summary = "Create a new car", description = "Create a new car with the given details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createCar(@Valid @RequestBody CreateCarRequestDto carDTO) {
        Car car = carMapper.toEntity(carDTO);
        car = carService.createCar(car);
        CarResponseDto createdCarDTO = carMapper.toDto(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCarDTO);
    }

    @Operation(summary = "Update a car", description = "Update the details of an existing car.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Car not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/{carId}")
    public ResponseEntity<?> updateCar(@PathVariable Long carId, @Valid @RequestBody UpdateCarRequestDto carDTO) {
        Car car = carMapper.toEntity(carDTO, carId);
        CarResponseDto updatedCarDTO = carMapper.toDto(car);
        return ResponseEntity.ok(updatedCarDTO);
    }

    @Operation(summary = "Delete a car", description = "Delete a car by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Car deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Car not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Long carId) {
        carService.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }
}
