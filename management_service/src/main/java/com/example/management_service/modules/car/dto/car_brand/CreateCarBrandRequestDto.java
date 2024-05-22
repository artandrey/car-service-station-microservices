package com.example.management_service.modules.car.dto.car_brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateCarBrandRequestDto {

    @NotBlank
    @NotNull
    @Schema(description = "The title of the car brand")
    private String title;
}
