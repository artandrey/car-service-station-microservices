package com.example.payment_service.modules.bill.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class CreateCashPaymentRequestDto {
    @NotNull
    @Schema(description = "The ID of the bill for which the cash payment is made")
    private Long orderId;

    @NotNull
    @Schema(description = "The amount paid in cash")
    private double paid;
}
