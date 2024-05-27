package com.example.management_service.modules.user.dto.worker_position;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateWorkerPositionRequestDto {
    @NotBlank
    @Schema(description = "The title of the worker position", required = true)
    private String title;

    @Schema(description = "The description of the worker position")
    private String description;
}
