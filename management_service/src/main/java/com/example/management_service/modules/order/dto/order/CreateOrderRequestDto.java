package com.example.management_service.modules.order.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderRequestDto {

    @NotNull
    @Schema(description = "ID of the car associated with the order", required = true)
    private Long carId;

    @NotNull
    @Schema(description = "ID of the client who placed the order", required = true)
    private Long clientId;

    @NotBlank
    @NotNull
    @Schema(description = "Description of the order", required = true)
    private String description;
}
