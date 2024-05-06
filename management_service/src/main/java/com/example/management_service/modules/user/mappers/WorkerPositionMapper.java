package com.example.management_service.modules.user.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.management_service.modules.user.dto.worker_position.CreateWorkerPositionRequestDto;
import com.example.management_service.modules.user.dto.worker_position.UpdateWorkerPositionRequestDto;
import com.example.management_service.modules.user.dto.worker_position.WorkerPositionResponseDto;
import com.example.management_service.modules.user.entities.WorkerPosition;

@Component
public class WorkerPositionMapper {
        private final ModelMapper modelMapper;

        public WorkerPositionMapper(ModelMapper modelMapper) {
                this.modelMapper = modelMapper;

                this.modelMapper.createTypeMap(CreateWorkerPositionRequestDto.class, WorkerPosition.class)
                                .addMappings(map -> map.skip(WorkerPosition::setId));

                this.modelMapper.createTypeMap(UpdateWorkerPositionRequestDto.class, WorkerPosition.class)
                                .addMappings(map -> map.skip(WorkerPosition::setId));
        }

        public WorkerPosition toEntity(CreateWorkerPositionRequestDto requestDto) {
                return modelMapper.map(requestDto, WorkerPosition.class);
        }

        public WorkerPositionResponseDto toDto(WorkerPosition entity) {
                return modelMapper.map(entity, WorkerPositionResponseDto.class);
        }

        public WorkerPosition toEntity(UpdateWorkerPositionRequestDto requestDto) {
                return modelMapper.map(requestDto, WorkerPosition.class);
        }
}
