package com.example.management_service.modules.car.dto.car_model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CarModelResponseDto {

    @Schema(description = "The unique identifier of the car model")
    private Long id;

    @Schema(description = "The title of the car model", required = true)
    private String title;

    @Schema(description = "The manufacturing year of the car model", required = true)
    private Integer year;
}
