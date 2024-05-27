package com.example.management_service.modules.car.dto.car_model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateCarModelRequestDto {

    @NotNull
    @Schema(description = "The unique identifier of the car brand associated with this model", required = true)
    private Long brandId;

    @NotBlank
    @NotNull
    @Schema(description = "The title of the car model", required = true)
    private String title;

    @NotNull
    @Schema(description = "The manufacturing year of the car model", required = true)
    private Integer year;
}
