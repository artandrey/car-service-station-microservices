package com.example.management_service.modules.car.dto.car;

import lombok.Data;

@Data
public class CarResponseDto {
    private Long id;
    private Long carModelId;
    private Long ownerId;
    private String vinCode;
    private String color;
}
