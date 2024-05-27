package com.example.payment_service.modules.bill.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@AllArgsConstructor
@Data
public class CreateBillRequestDto {
    @NotNull
    @Schema(description = "The ID of the order for which the bill is created")
    private Long orderId;
    
    @NotNull
    @Schema(description = "The total amount of the bill")
    private double amount;
    
    @NotBlank
    @Schema(description = "The ID of the client associated with the bill")
    private String clientId;
    
    @Schema(description = "The amount already paid for the bill")
    private double paid;
    
    @Schema(description = "The remaining amount to be paid for the bill")
    private double rest;
}
