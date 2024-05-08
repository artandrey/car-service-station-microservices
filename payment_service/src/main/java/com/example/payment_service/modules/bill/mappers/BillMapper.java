package com.example.payment_service.modules.bill.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.payment_service.modules.bill.dto.BillResponseDto;
import com.example.payment_service.modules.bill.entities.Bill;

@Component
public class BillMapper {
    private final ModelMapper modelMapper;

    public BillMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BillResponseDto toDto(Bill entity) {
        return modelMapper.map(entity, BillResponseDto.class);
    }
}