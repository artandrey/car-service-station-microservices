package com.example.management_service.modules.user.dto.worker_profile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class CreateWorkerProfileRequestDto {
    @NotNull
    @Schema(description = "The ID of the user associated with the worker profile", required = true)
    private String userId;

    @NotNull
    @Schema(description = "The ID of the worker position", required = true)
    private Long positionId;

    @NotNull
    @Schema(description = "The date of hiring", required = true)
    private Date hireDate;

    @NotNull
    @Schema(description = "The salary of the worker", required = true)
    private double salary;
}
