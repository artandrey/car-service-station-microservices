package com.example.management_service.modules.car.dto.car_part;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateCarPartRequestDto {

    @NotBlank
    @Schema(description = "The title of the car part")
    private String title;

    @Positive(message = "Price must be positive")
    @Schema(description = "The price of the car part")
    private double price;
}
