package com.example.management_service.modules.order.dto.order_task;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import com.example.management_service.modules.order.entities.CompletionStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Set;

@Data
public class CreateOrderTaskRequestDto {

    @NotNull
    @Schema(description = "Status of the task", required = true)
    private CompletionStatus taskStatus;

    @NotNull
    @Schema(description = "Price for the work", required = true)
    private double workPrice;

    @Schema(description = "Date when the task was created")
    private Date createdAt;

    @Schema(description = "Date when the task was started")
    private Date startedAt;

    @Schema(description = "Date when the task was completed")
    private Date completedAt;

    @NotBlank
    @Schema(description = "Title of the task", required = true)
    private String title;

    @Schema(description = "IDs of the parts used in the task")
    private Set<Long> usedParts;

    @Schema(description = "IDs of users assigned to the task")
    private Set<Long> assignedTo;

    @NotNull
    @Schema(description = "ID of the order associated with the task", required = true)
    private Long orderId;
}
