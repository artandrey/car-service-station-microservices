package com.example.management_service.modules.car.dto.car_part;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateCarPartRequestDto {
    @NotBlank
    private String title;

    @Positive(message = "Price must be positive")
    private double price;
}