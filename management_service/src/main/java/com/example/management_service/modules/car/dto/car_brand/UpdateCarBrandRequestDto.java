package com.example.management_service.modules.car.dto.car_brand;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UpdateCarBrandRequestDto {
    @NotBlank
    private String title;
}
