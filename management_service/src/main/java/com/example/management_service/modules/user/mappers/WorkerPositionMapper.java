package com.example.management_service.modules.user.mappers;

import com.example.management_service.modules.user.dto.worker_position.CreateWorkerPositionRequestDto;
import com.example.management_service.modules.user.dto.worker_position.UpdateWorkerPositionRequestDto;
import com.example.management_service.modules.user.dto.worker_position.WorkerPositionResponseDto;
import com.example.management_service.modules.user.entities.WorkerPosition;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkerPositionMapper {
    private final ModelMapper modelMapper;

    public WorkerPositionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public WorkerPosition toEntity(CreateWorkerPositionRequestDto requestDto) {
        return modelMapper.map(requestDto, WorkerPosition.class);
    }

    public WorkerPosition toEntity(UpdateWorkerPositionRequestDto requestDto) {
        return modelMapper.map(requestDto, WorkerPosition.class);
    }

    public WorkerPositionResponseDto toDto(WorkerPosition entity) {
        return modelMapper.map(entity, WorkerPositionResponseDto.class);
    }

    public List<WorkerPositionResponseDto> toDto(List<WorkerPosition> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public WorkerPosition updateFrom(WorkerPosition updatedEntity, WorkerPosition entity) {
        modelMapper.map(updatedEntity, entity);
        return entity;
    }
}
