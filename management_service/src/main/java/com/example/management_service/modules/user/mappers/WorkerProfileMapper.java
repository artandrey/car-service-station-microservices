package com.example.management_service.modules.user.mappers;

import com.example.management_service.modules.user.dto.worker_profile.CreateWorkerProfileRequestDto;
import com.example.management_service.modules.user.dto.worker_profile.UpdateWorkerProfileRequestDto;
import com.example.management_service.modules.user.dto.worker_profile.WorkerProfileResponseDto;
import com.example.management_service.modules.user.entities.WorkerProfile;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkerProfileMapper {
    private final ModelMapper modelMapper;

    public WorkerProfileMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public WorkerProfile toEntity(CreateWorkerProfileRequestDto requestDto) {
        return modelMapper.map(requestDto, WorkerProfile.class);
    }

    public WorkerProfile toEntity(UpdateWorkerProfileRequestDto requestDto) {
        return modelMapper.map(requestDto, WorkerProfile.class);
    }

    public WorkerProfileResponseDto toDto(WorkerProfile entity) {
        return modelMapper.map(entity, WorkerProfileResponseDto.class);
    }

    public List<WorkerProfileResponseDto> toDto(List<WorkerProfile> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public WorkerProfile updateFrom(WorkerProfile updatedEntity, WorkerProfile entity) {
        modelMapper.map(updatedEntity, entity);
        return entity;
    }
}
