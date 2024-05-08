package com.example.management_service.modules.car.dto.car_brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarBrandResponseDto {
    private Long id;
    private String title;
}
