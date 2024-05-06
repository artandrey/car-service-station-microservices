package com.example.management_service.modules.car.dto.car_model;

import lombok.Data;

@Data
public class CarModelResponseDto {
    private Long id;
    private String title;
    private Integer year;
}
