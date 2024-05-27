package com.example.management_service.modules.car.dto.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateCarRequestDto {

    @NotNull
    @Schema(description = "The unique identifier of the car model", example = "101")
    private Long carModelId;

    @NotNull
    @Schema(description = "The unique identifier of the owner", example = "1001")
    private String ownerId;

    @Size(min = 17, max = 17)
    @Schema(description = "The Vehicle Identification Number (VIN) code of the car", example = "1HGCM82633A123456")
    private String vinCode;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 30)
    @Schema(description = "The color of the car", example = "Red")
    private String color;
}
