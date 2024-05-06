package com.example.management_service.modules.car.dto.car_brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateCarBrandRequestDto {
    private String title;
}
