package com.example.management_service.modules.order.dto.order;

import lombok.Data;

import java.util.Date;

@Data
public class OrderResponseDto {
    private Long id;

    private Long carId;

    private Long clientId;

    private Date createdAt;

    private Date completedAt;

    private String description;
}