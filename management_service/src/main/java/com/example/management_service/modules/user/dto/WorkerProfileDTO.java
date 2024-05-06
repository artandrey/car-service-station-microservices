package com.example.management_service.modules.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class WorkerProfileDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long positionId;

    @NotNull
    private Date hireDate;

    private Date fireDate;

    @NotNull
    private double salary;
}