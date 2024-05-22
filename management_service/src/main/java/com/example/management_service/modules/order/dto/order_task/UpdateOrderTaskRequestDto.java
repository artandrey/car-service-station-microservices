package com.example.management_service.modules.order.dto.order_task;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;
import java.util.Set;

import com.example.management_service.modules.order.entities.CompletionStatus;

@Data
public class UpdateOrderTaskRequestDto {

    @Schema(description = "Status of the task")
    private CompletionStatus taskStatus;

    @Schema(description = "Price for the work")
    private double workPrice;

    @Schema(description = "Date when the task was created")
    private Date createdAt;

    @Schema(description = "Date when the task was started")
    private Date startedAt;

    @Schema(description = "Date when the task was completed")
    private Date completedAt;

    @Schema(description = "Title of the task")
    private String title;

    @Schema(description = "IDs of the parts used in the task")
    private Set<Long> usedParts;

    @Schema(description = "IDs of users assigned to the task")
    private Set<Long> assignedTo;
}
