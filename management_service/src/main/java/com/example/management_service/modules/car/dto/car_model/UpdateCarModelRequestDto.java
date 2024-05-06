package com.example.management_service.modules.car.dto.car_model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCarModelRequestDto {
    @NotBlank
    private String title;
    private Integer year;
}