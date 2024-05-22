package com.example.management_service.modules.car.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.example.management_service.modules.car.dto.car_part.CarPartResponseDto;
import com.example.management_service.modules.car.dto.car_part.CreateCarPartRequestDto;
import com.example.management_service.modules.car.dto.car_part.UpdateCarPartRequestDto;
import com.example.management_service.modules.car.entities.CarPart;
import com.example.management_service.modules.car.mappers.CarPartMapper;
import com.example.management_service.modules.car.services.car_part.ICarPartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/car-parts")
@Tag(name = "Car Part Management", description = "APIs to manage car parts")
public class CarPartController {
    @Autowired
    private ICarPartService carPartService;

    @Autowired
    private CarPartMapper carPartMapper;

    @Operation(summary = "Get all car parts", description = "Retrieve a list of all car parts.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<CarPartResponseDto>> getAllCarParts() {
        List<CarPart> carParts = carPartService.getAllCarParts();
        return ResponseEntity.ok(carPartMapper.toDto(carParts));
    }

    @Operation(summary = "Get car part by ID", description = "Retrieve a car part by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved car part", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarPartResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Car part not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarPartResponseDto> getCarPartById(@PathVariable Long id) {
        CarPart carPart = carPartService.getCarPartById(id);
        return ResponseEntity.ok(carPartMapper.toDto(carPart));
    }

    @Operation(summary = "Create a new car part", description = "Create a new car part with the given details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car part created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarPartResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CarPartResponseDto> createCarPart(
            @Valid @RequestBody CreateCarPartRequestDto requestDto) {
        CarPart carPart = carPartService.createCarPart(carPartMapper.toEntity(requestDto));
        return new ResponseEntity<>(carPartMapper.toDto(carPart), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a car part", description = "Update the details of an existing car part.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car part updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarPartResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Car part not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CarPartResponseDto> updateCarPart(@PathVariable Long id,
            @Valid @RequestBody UpdateCarPartRequestDto requestDto) {
        CarPart updatedCarPart = carPartService.updateCarPart(id, carPartMapper.toEntity(requestDto));
        return ResponseEntity.ok(carPartMapper.toDto(updatedCarPart));
    }

    @Operation(summary = "Delete a car part", description = "Delete a car part by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Car part deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Car part not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarPart(@PathVariable Long id) {
        carPartService.deleteCarPart(id);
        return ResponseEntity.noContent().build();
    }
}
