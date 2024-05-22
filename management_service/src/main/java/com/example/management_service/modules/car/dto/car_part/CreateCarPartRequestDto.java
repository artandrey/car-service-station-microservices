package com.example.management_service.modules.car.dto.car_part;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateCarPartRequestDto {

    @NotNull
    @NotBlank()
    @Schema(description = "The title of the car part", required = true)
    private String title;

    @NotNull
    @Positive()
    @Schema(description = "The price of the car part", required = true)
    private double price;
}
