package com.example.payment_service.modules.bill.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BillResponseDto {
    private Long id;
    private Long orderId;
    private double amount;
    private double paid;
    private double rest;
    private Date createdAt;
    private Date warrantyExpiresAt;
}