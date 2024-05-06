package com.example.management_service.modules.car.dto.car_part;

import lombok.Data;

@Data
public class CarPartResponseDto {
    private Long id;

    private String title;

    private double price;
}