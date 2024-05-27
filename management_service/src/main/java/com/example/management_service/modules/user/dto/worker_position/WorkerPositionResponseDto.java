package com.example.management_service.modules.user.dto.worker_position;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class WorkerPositionResponseDto {
    @Schema(description = "The ID of the worker position")
    private Long id;

    @Schema(description = "The title of the worker position")
    private String title;

    @Schema(description = "The description of the worker position")
    private String description;
}
