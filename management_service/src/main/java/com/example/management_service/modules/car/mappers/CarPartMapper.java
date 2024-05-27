package com.example.management_service.modules.car.mappers;

import com.example.management_service.modules.car.dto.car_part.CarPartResponseDto;
import com.example.management_service.modules.car.dto.car_part.CreateCarPartRequestDto;
import com.example.management_service.modules.car.dto.car_part.UpdateCarPartRequestDto;
import com.example.management_service.modules.car.entities.CarModel;
import com.example.management_service.modules.car.entities.CarPart;
import com.example.management_service.shared.services.ConverterService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarPartMapper {
    private final ModelMapper modelMapper;

    public CarPartMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.createTypeMap(CreateCarPartRequestDto.class, CarPart.class)
                .addMappings(mapper -> mapper.using(ConverterService.idsToEntities(CarModel::new))
                        .map(CreateCarPartRequestDto::getModels, CarPart::setCarModels));

    }

    public CarPart toEntity(CreateCarPartRequestDto requestDto) {
        return modelMapper.map(requestDto, CarPart.class);
    }

    public CarPartResponseDto toDto(CarPart entity) {
        return modelMapper.map(entity, CarPartResponseDto.class);
    }

    public CarPart updateFromEntity(CarPart updatedEntity, CarPart entity) {
        modelMapper.map(updatedEntity, entity);
        return updatedEntity;
    }

    public List<CarPartResponseDto> toDto(List<CarPart> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CarPart toEntity(UpdateCarPartRequestDto requestDto) {
        return modelMapper.map(requestDto, CarPart.class);
    }
}
