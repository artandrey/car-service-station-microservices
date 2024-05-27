package com.example.payment_service.modules.bill.dto;

import lombok.Data;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class BillResponseDto {
    @Schema(description = "The ID of the bill")
    private Long id;

    @Schema(description = "The ID of the order associated with the bill")
    private Long orderId;

    @Schema(description = "The total amount of the bill")
    private double amount;

    @Schema(description = "The amount paid for the bill")
    private double paid;

    @Schema(description = "The remaining amount to be paid")
    private double rest;

    @Schema(description = "The date when the bill was created")
    private Date createdAt;

    @Schema(description = "The date when the warranty expires")
    private Date warrantyExpiresAt;
}
