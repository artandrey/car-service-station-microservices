package com.example.management_service.modules.user.dto.worker_profile;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateWorkerProfileRequestDto {

    private Long positionId;

    private Date hireDate;

    private Date fireDate;

    private double salary;
}
