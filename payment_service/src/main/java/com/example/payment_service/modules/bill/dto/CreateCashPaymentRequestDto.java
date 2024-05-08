package com.example.payment_service.modules.bill.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCashPaymentRequestDto {
    @NotNull
    private Long orderId;
    @NotNull
    private double paid;
}