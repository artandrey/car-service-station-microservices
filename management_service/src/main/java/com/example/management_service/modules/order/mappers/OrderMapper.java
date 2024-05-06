package com.example.management_service.modules.order.mappers;

import com.example.management_service.modules.order.dto.CreateOrderRequestDto;
import com.example.management_service.modules.order.dto.OrderResponseDto;
import com.example.management_service.modules.order.dto.UpdateOrderRequestDto;
import com.example.management_service.modules.order.entities.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Order toEntity(CreateOrderRequestDto requestDto) {
        return modelMapper.map(requestDto, Order.class);
    }

    public OrderResponseDto toDto(Order entity) {
        return modelMapper.map(entity, OrderResponseDto.class);
    }

    public List<OrderResponseDto> toDto(List<Order> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Order toEntity(UpdateOrderRequestDto requestDto, Long id) {
        Order order = modelMapper.map(requestDto, Order.class);
        order.setId(id);
        return order;
    }
}