package com.example.management_service.modules.order.dto.order;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateOrderRequestDto {

    @NotBlank
    private String description;
}