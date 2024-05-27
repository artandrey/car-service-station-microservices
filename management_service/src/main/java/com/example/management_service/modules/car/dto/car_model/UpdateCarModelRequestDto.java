package com.example.management_service.modules.car.dto.car_model;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateCarModelRequestDto {

    @NotBlank
    @Schema(description = "The title of the car model")
    private String title;

    @Schema(description = "The manufacturing year of the car model")
    private Integer year;
}
