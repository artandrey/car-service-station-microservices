package com.example.management_service.modules.order.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderRequestDto {
    @NotNull
    private Long carId;

    @NotNull
    private Long clientId;

    @NotBlank
    @NotNull
    private String description;
}