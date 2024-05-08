package com.example.management_service.modules.order.dto.order_task;

import lombok.Data;

import java.util.Date;
import java.util.Set;

import com.example.management_service.modules.order.entities.CompletionStatus;

@Data
public class UpdateOrderTaskRequestDto {
    private CompletionStatus taskStatus;

    private double workPrice;

    private Date createdAt;

    private Date startedAt;

    private Date completedAt;

    private String title;

    private Set<Long> usedParts;

    private Set<Long> assignedTo;
}