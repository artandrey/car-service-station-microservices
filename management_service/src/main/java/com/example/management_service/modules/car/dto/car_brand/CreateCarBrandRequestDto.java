package com.example.management_service.modules.car.dto.car_brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateCarBrandRequestDto {
    @NotBlank
    @NotNull

    private String title;
}
