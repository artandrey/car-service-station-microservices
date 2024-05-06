package com.example.management_service.modules.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderRequestDto {
    @NotNull
    private Long carId;

    @NotNull
    private Long clientId;

    private String description;
}