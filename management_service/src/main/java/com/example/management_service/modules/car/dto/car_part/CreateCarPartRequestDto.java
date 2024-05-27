package com.example.management_service.modules.car.dto.car_part;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateCarPartRequestDto {
    @Schema(description = "Car models ids")
    private Long id;
    private Set<Long> models;

    @NotNull
    @NotBlank()
    @Schema(description = "The title of the car part", required = true)
    private String title;

    @NotNull
    @Positive()
    @Schema(description = "The price of the car part", required = true)
    private double price;
}
