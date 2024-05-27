package com.example.management_service.modules.car.dto.car;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateCarRequestDto {

    @NotNull
    @Schema(description = "The unique identifier of the car model")
    private Long carModelId;

    @NotNull
    @Schema(description = "The unique identifier of the owner")
    private String ownerId;

    @NotNull
    @Size(min = 17, max = 17)
    @Schema(description = "The Vehicle Identification Number (VIN) code of the car")
    private String vinCode;

    @NotNull
    @Size(min = 3, max = 30)
    @Schema(description = "The color of the car")
    private String color;
}
