package com.example.management_service.modules.order.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateOrderRequestDto {

    @NotBlank
    @Schema(description = "Updated description of the order", required = true)
    private String description;
}
