package com.example.management_service.modules.user.controllers;

import com.example.management_service.modules.user.dto.worker_profile.CreateWorkerProfileRequestDto;
import com.example.management_service.modules.user.dto.worker_profile.UpdateWorkerProfileRequestDto;
import com.example.management_service.modules.user.dto.worker_profile.WorkerProfileResponseDto;
import com.example.management_service.modules.user.entities.WorkerProfile;
import com.example.management_service.modules.user.mappers.WorkerProfileMapper;
import com.example.management_service.modules.user.services.worker_profile.IWorkerProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker-profiles")
@Tag(name = "Worker Profiles", description = "Endpoints for managing worker profiles")
public class WorkerProfileController {
    @Autowired
    private IWorkerProfileService workerProfileService;

    @Autowired
    private WorkerProfileMapper workerProfileMapper;

    @GetMapping
    @Operation(summary = "Get all worker profiles", description = "Retrieve a list of all worker profiles.")
    public ResponseEntity<List<WorkerProfileResponseDto>> getAllWorkerProfiles() {
        List<WorkerProfile> workerProfiles = workerProfileService.getAllWorkerProfiles();
        return ResponseEntity.ok(workerProfileMapper.toDto(workerProfiles));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get worker profile by ID", description = "Retrieve a worker profile by its ID.")
    public ResponseEntity<WorkerProfileResponseDto> getWorkerProfileById(@PathVariable Long id) {
        WorkerProfile workerProfile = workerProfileService.getWorkerProfileById(id);
        return ResponseEntity.ok(workerProfileMapper.toDto(workerProfile));
    }

    @PostMapping
    @Operation(summary = "Create a new worker profile", description = "Create a new worker profile.")
    public ResponseEntity<WorkerProfileResponseDto> createWorkerProfile(
            @Valid @RequestBody CreateWorkerProfileRequestDto requestDto) {
        WorkerProfile workerProfile = workerProfileService
                .createWorkerProfile(workerProfileMapper.toEntity(requestDto));
        return new ResponseEntity<>(workerProfileMapper.toDto(workerProfile), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update worker profile", description = "Update an existing worker profile.")
    public ResponseEntity<WorkerProfileResponseDto> updateWorkerProfile(@PathVariable Long id,
            @Valid @RequestBody UpdateWorkerProfileRequestDto requestDto) {

        WorkerProfile updatedWorkerProfile = workerProfileService.updateWorkerProfile(id,
                workerProfileMapper.toEntity(requestDto));
        return ResponseEntity.ok(workerProfileMapper.toDto(updatedWorkerProfile));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete worker profile", description = "Delete a worker profile by its ID.")
    public ResponseEntity<Void> deleteWorkerProfile(@PathVariable Long id) {
        workerProfileService.deleteWorkerProfile(id);
        return ResponseEntity.noContent().build();
    }
}
