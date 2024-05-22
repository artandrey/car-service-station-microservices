package com.example.management_service.modules.car.dto.car;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CarResponseDto {

    @Schema(description = "The unique identifier of the car", example = "1")
    private Long id;

    @Schema(description = "The unique identifier of the car model", example = "101")
    private Long carModelId;

    @Schema(description = "The unique identifier of the owner", example = "1001")
    private Long ownerId;

    @Schema(description = "The Vehicle Identification Number (VIN) code of the car", example = "1HGCM82633A123456")
    private String vinCode;

    @Schema(description = "The color of the car", example = "Red")
    private String color;
}
