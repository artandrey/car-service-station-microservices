package com.example.management_service.modules.car.dto.car;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCarRequestDto {
    @NotNull
    private Long id;

    @NotNull
    private Long carModelId;

    @NotNull
    private Long ownerId;

    @NotNull
    @Size(min = 17, max = 17)
    private String vinCode;

    @NotNull
    @Size(min = 3, max = 30)
    private String color;
}
