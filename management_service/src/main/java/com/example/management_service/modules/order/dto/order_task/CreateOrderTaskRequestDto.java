package com.example.management_service.modules.order.dto.order_task;

import lombok.Data;

import com.example.management_service.modules.order.entities.CompletionStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Set;

@Data
public class CreateOrderTaskRequestDto {
    @NotNull
    private CompletionStatus taskStatus;

    @NotNull
    private double workPrice;

    private Date createdAt;

    private Date startedAt;

    private Date completedAt;

    @NotBlank
    private String title;

    private Set<Long> usedParts;

    private Set<Long> assignedTo;

    @NotNull
    private Long orderId;
}