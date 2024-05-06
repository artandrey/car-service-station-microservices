package com.example.management_service.modules.car.dto.car_model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCarModelRequestDto {
    @NotBlank
    private Long brandId;
    @NotBlank
    private String title;
    @NotBlank
    private Integer year;
}
