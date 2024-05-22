package com.example.payment_service.modules.bill.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateBillRequestDto {
    @NotNull
    private Long orderId;
    @NotNull
    private double amount;
    @NotBlank
    private String clientId;
    private double paid;
    private double rest;
}
