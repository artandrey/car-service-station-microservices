package com.example.management_service.modules.car.dto.car_brand;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateCarBrandRequestDto {
    private String title;
}