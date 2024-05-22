package com.example.management_service.modules.order.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

@Data
public class OrderResponseDto {

    @Schema(description = "ID of the order")
    private Long id;

    @Schema(description = "ID of the car associated with the order")
    private Long carId;

    @Schema(description = "ID of the client who placed the order")
    private Long clientId;

    @Schema(description = "Date when the order was created")
    private Date createdAt;

    @Schema(description = "Date when the order was completed")
    private Date completedAt;

    @Schema(description = "Description of the order")
    private String description;
}
