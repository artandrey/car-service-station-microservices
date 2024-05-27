package com.example.management_service.modules.car.dto.car_part;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CarPartResponseDto {

    @Schema(description = "The unique identifier of the car part")
    private Long id;

    @Schema(description = "The title of the car part")
    private String title;

    @Schema(description = "The price of the car part")
    private double price;
}
