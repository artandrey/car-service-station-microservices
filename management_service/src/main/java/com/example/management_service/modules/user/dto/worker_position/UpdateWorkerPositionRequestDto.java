package com.example.management_service.modules.user.dto.worker_position;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateWorkerPositionRequestDto {
    @Schema(description = "The updated title of the worker position")
    private String title;

    @Schema(description = "The updated description of the worker position")
    private String description;
}
