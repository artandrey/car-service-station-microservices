package com.example.management_service.modules.user.dto.worker_profile;

import lombok.Data;

import java.util.Date;

import jakarta.validation.constraints.NotNull;

@Data
public class CreateWorkerProfileRequestDto {
    @NotNull
    private Long userId;

    @NotNull
    private Long positionId;
    @NotNull

    private Date hireDate;
    @NotNull

    private Date fireDate;
    @NotNull

    private double salary;
}
