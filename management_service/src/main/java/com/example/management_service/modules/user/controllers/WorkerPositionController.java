package com.example.management_service.modules.user.controllers;

import com.example.management_service.modules.user.dto.worker_position.CreateWorkerPositionRequestDto;
import com.example.management_service.modules.user.dto.worker_position.UpdateWorkerPositionRequestDto;
import com.example.management_service.modules.user.dto.worker_position.WorkerPositionResponseDto;
import com.example.management_service.modules.user.entities.WorkerPosition;
import com.example.management_service.modules.user.mappers.WorkerPositionMapper;
import com.example.management_service.modules.user.services.worker_position.IWorkerPositionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker-positions")
@Tag(name = "Worker Positions", description = "Endpoints for managing worker positions")
public class WorkerPositionController {
    @Autowired
    private IWorkerPositionService workerPositionService;

    @Autowired
    private WorkerPositionMapper workerPositionMapper;

    @GetMapping
    @Operation(summary = "Get all worker positions", description = "Retrieve a list of all worker positions.")
    public ResponseEntity<List<WorkerPositionResponseDto>> getAllWorkerPositions() {
        List<WorkerPosition> workerPositions = workerPositionService.getAllWorkerPositions();
        return ResponseEntity.ok(workerPositionMapper.toDto(workerPositions));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get worker position by ID", description = "Retrieve a worker position by its ID.")
    public ResponseEntity<WorkerPositionResponseDto> getWorkerPositionById(@PathVariable Long id) {
        WorkerPosition workerPosition = workerPositionService.getWorkerPositionById(id);
        return ResponseEntity.ok(workerPositionMapper.toDto(workerPosition));
    }

    @PostMapping
    @Operation(summary = "Create a new worker position", description = "Create a new worker position.")
    public ResponseEntity<WorkerPositionResponseDto> createWorkerPosition(
            @Valid @RequestBody CreateWorkerPositionRequestDto requestDto) {
        WorkerPosition workerPosition = workerPositionService
                .createWorkerPosition(workerPositionMapper.toEntity(requestDto));
        return new ResponseEntity<>(workerPositionMapper.toDto(workerPosition), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update worker position", description = "Update an existing worker position.")
    public ResponseEntity<WorkerPositionResponseDto> updateWorkerPosition(@PathVariable Long id,
            @Valid @RequestBody UpdateWorkerPositionRequestDto requestDto) {
        WorkerPosition updatedWorkerPosition = workerPositionService.updateWorkerPosition(id,
                workerPositionMapper.toEntity(requestDto));
        return ResponseEntity.ok(workerPositionMapper.toDto(updatedWorkerPosition));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete worker position", description = "Delete a worker position by its ID.")
    public ResponseEntity<Void> deleteWorkerPosition(@PathVariable Long id) {
        workerPositionService.deleteWorkerPosition(id);
        return ResponseEntity.noContent().build();
    }
}
