package com.example.management_service.modules.car.dto.car_model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarModelResponseDto {
    @NotNull
    private Long id;
    @NotBlank
    @NotNull
    private String title;
    @NotNull
    private Integer year;
}
