package com.example.management_service.modules.user.dto.worker_profile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class WorkerProfileResponseDto {
    @Schema(description = "The ID of the worker profile")
    private Long id;

    @Schema(description = "The ID of the user associated with the worker profile")
    private String userId;

    @Schema(description = "The ID of the worker position")
    private Long positionId;

    @Schema(description = "The date of hiring")
    private Date hireDate;

    @Schema(description = "The date of termination")
    private Date fireDate;

    @Schema(description = "The salary of the worker")
    private double salary;
}
