package com.example.management_service.modules.car.dto.car_brand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarBrandResponseDto {

    @Schema(description = "The unique identifier of the car brand")
    private Long id;

    @Schema(description = "The title of the car brand")
    private String title;
}
