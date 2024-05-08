package com.example.management_service.modules.user.dto.worker_position;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateWorkerPositionRequestDto {
    @NotBlank
    private String title;

    private String description;
}
