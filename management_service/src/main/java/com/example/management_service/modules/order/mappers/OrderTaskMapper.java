package com.example.management_service.modules.order.mappers;

import com.example.management_service.modules.car.entities.CarPart;
import com.example.management_service.modules.order.dto.order_task.CreateOrderTaskRequestDto;
import com.example.management_service.modules.order.dto.order_task.OrderTaskResponseDto;
import com.example.management_service.modules.order.dto.order_task.UpdateOrderTaskRequestDto;
import com.example.management_service.modules.order.entities.Order;
import com.example.management_service.modules.order.entities.OrderTask;
import com.example.management_service.modules.user.entities.WorkerProfile;
import com.example.management_service.shared.services.ConverterService;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderTaskMapper {
    private final ModelMapper modelMapper;

    public OrderTaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        this.modelMapper.createTypeMap(CreateOrderTaskRequestDto.class, OrderTask.class)
                .addMappings(map -> map.using(ConverterService.idToEntity(Order::new))
                        .map(CreateOrderTaskRequestDto::getOrderId, OrderTask::setOrder))
                .addMappings(mapper -> mapper.using(ConverterService.idsToEntities(CarPart::new))
                        .map(CreateOrderTaskRequestDto::getUsedParts, OrderTask::setUsedParts))
                .addMappings(mapper -> mapper.using(ConverterService.idsToEntities(WorkerProfile::new))
                        .map(CreateOrderTaskRequestDto::getAssignedTo, OrderTask::setAssignedTo));
        this.modelMapper.createTypeMap(UpdateOrderTaskRequestDto.class, OrderTask.class)
                .addMappings(mapper -> mapper.using(ConverterService.idsToEntities(CarPart::new))
                        .map(UpdateOrderTaskRequestDto::getUsedParts, OrderTask::setUsedParts))
                .addMappings(mapper -> mapper.using(ConverterService.idsToEntities(WorkerProfile::new))
                        .map(UpdateOrderTaskRequestDto::getAssignedTo, OrderTask::setAssignedTo));
        this.modelMapper.createTypeMap(OrderTask.class, OrderTask.class).addMappings(mapper -> {
            mapper.skip(OrderTask::setUsedParts);
            mapper.skip(OrderTask::setAssignedTo);
        });

        modelMapper.createTypeMap(OrderTask.class, OrderTaskResponseDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getOrder().getId(), OrderTaskResponseDto::setOrderId);
        }).addMappings(mapper -> {
            mapper.using(ctx -> {
                Set<CarPart> carParts = (Set<CarPart>) ctx.getSource();
                Hibernate.initialize(carParts);
                return carParts.stream()
                        .map(CarPart::getId)
                        .collect(Collectors.toSet());
            }).map(OrderTask::getUsedParts, OrderTaskResponseDto::setUsedParts);

            mapper.using(ctx -> {
                Set<WorkerProfile> workerProfiles = (Set<WorkerProfile>) ctx.getSource();
                Hibernate.initialize(workerProfiles);
                return workerProfiles.stream()
                        .map(WorkerProfile::getId)
                        .collect(Collectors.toSet());
            }).map(OrderTask::getAssignedTo, OrderTaskResponseDto::setAssignedTo);
        });
    }

    public OrderTask toEntity(CreateOrderTaskRequestDto requestDto) {
        return modelMapper.map(requestDto, OrderTask.class);
    }

    public OrderTask toEntity(UpdateOrderTaskRequestDto requestDto) {
        return modelMapper.map(requestDto, OrderTask.class);
    }

    public OrderTaskResponseDto toDto(OrderTask entity) {
        OrderTaskResponseDto dto = modelMapper.map(entity, OrderTaskResponseDto.class);
        dto.setUsedParts(entity.getUsedParts().stream().map(part -> part.getId()).collect(Collectors.toSet()));
        dto.setAssignedTo(entity.getAssignedTo().stream().map(profile -> profile.getId()).collect(Collectors.toSet()));
        return dto;
    }

    public List<OrderTaskResponseDto> toDto(List<OrderTask> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public OrderTask updateFromEntity(OrderTask updatedEntity, OrderTask entity) {
        modelMapper.map(updatedEntity, entity);

        entity.getUsedParts().addAll(updatedEntity.getUsedParts());
        entity.getAssignedTo().addAll(updatedEntity.getAssignedTo());

        return entity;
    }
}