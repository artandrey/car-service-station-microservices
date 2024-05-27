package com.example.management_service.modules.user.dto.worker_profile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateWorkerProfileRequestDto {

    @Schema(description = "The updated ID of the worker position")
    private Long positionId;

    @Schema(description = "The updated date of hiring")
    private Date hireDate;

    @Schema(description = "The updated date of termination")
    private Date fireDate;

    @Schema(description = "The updated salary of the worker")
    private double salary;
}
