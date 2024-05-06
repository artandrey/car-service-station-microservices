package com.example.management_service.modules.order.mappers;

import com.example.management_service.modules.order.dto.order_task.CreateOrderTaskRequestDto;
import com.example.management_service.modules.order.dto.order_task.OrderTaskResponseDto;
import com.example.management_service.modules.order.dto.order_task.UpdateOrderTaskRequestDto;
import com.example.management_service.modules.order.entities.OrderTask;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderTaskMapper {
    private final ModelMapper modelMapper;

    public OrderTaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }

    public OrderTask toEntity(CreateOrderTaskRequestDto requestDto) {
        return modelMapper.map(requestDto, OrderTask.class);
    }

    public OrderTask toEntity(UpdateOrderTaskRequestDto requestDto) {
        return modelMapper.map(requestDto, OrderTask.class);
    }

    public OrderTaskResponseDto toDto(OrderTask entity) {
        return modelMapper.map(entity, OrderTaskResponseDto.class);
    }

    public List<OrderTaskResponseDto> toDto(List<OrderTask> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public OrderTask updateFromEntity(OrderTask updatedEntity, OrderTask entity) {
        modelMapper.map(updatedEntity, entity);
        return updatedEntity;
    }
}