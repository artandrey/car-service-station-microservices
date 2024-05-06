package com.example.management_service.modules.car.dto.car_part;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateCarPartRequestDto {
    @NotNull
    @NotBlank()
    private String title;

    @NotNull
    @Positive()
    private double price;
}