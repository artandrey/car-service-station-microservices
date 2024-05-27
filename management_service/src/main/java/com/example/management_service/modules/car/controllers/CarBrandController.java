package com.example.management_service.modules.car.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/car-brands")
@Tag(name = "Car Brand Management", description = "APIs to manage car brands")
public class CarBrandController {
    @Autowired
    private ICarBrandService carBrandService;
    @Autowired
    private CarBrandMapper carBrandMapper;

    @Operation(summary = "Get all car brands", description = "Retrieve a list of all car brands.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarBrandResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<CarBrandResponseDto>> getAllCarBrands() {
        List<CarBrand> carBrands = carBrandService.getAllCarBrands();
        return ResponseEntity.ok(carBrands.stream().map(carBrandMapper::toDto).toList());
    }

    @Operation(summary = "Get car brand by ID", description = "Retrieve a car brand by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved car brand", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarBrandResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Car brand not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarBrandResponseDto> getCarBrandById(@PathVariable Long id) {
        CarBrand carBrand = carBrandService.getCarBrandById(id);
        return ResponseEntity.ok(carBrandMapper.toDto(carBrand));
    }

    @Operation(summary = "Create a new car brand", description = "Create a new car brand with the given details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car brand created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarBrandResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CarBrandResponseDto> createCarBrand(
            @Valid @RequestBody CreateCarBrandRequestDto createCarBrandRequestDto) {
        CarBrand carBrand = carBrandService.createCarBrand(carBrandMapper.toEntity(createCarBrandRequestDto));
        return new ResponseEntity<>(carBrandMapper.toDto(carBrand), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a car brand", description = "Update the details of an existing car brand.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car brand updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarBrandResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Car brand not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CarBrandResponseDto> updateCarBrand(@PathVariable Long id,
            @Valid @RequestBody UpdateCarBrandRequestDto carBrandRequestDto) {
        CarBrand carBrand = carBrandMapper.toEntity(carBrandRequestDto);

        CarBrand updatedCarBrand = carBrandService.updateCarBrand(id, carBrand);
        return ResponseEntity.ok(carBrandMapper.toDto(updatedCarBrand));
    }

    @Operation(summary = "Delete a car brand", description = "Delete a car brand by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Car brand deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Car brand not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarBrand(@PathVariable Long id) {
        carBrandService.deleteCarBrand(id);
        return ResponseEntity.noContent().build();
    }
}
