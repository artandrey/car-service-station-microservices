package com.example.management_service.modules.car.mappers;

import com.example.management_service.modules.car.dto.car_brand.CarBrandResponseDto;
import com.example.management_service.modules.car.dto.car_brand.CreateCarBrandRequestDto;
import com.example.management_service.modules.car.dto.car_brand.UpdateCarBrandRequestDto;
import com.example.management_service.modules.car.entities.CarBrand;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CarBrandMapper {
    private final ModelMapper modelMapper;

    public CarBrandMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CarBrand toEntity(CreateCarBrandRequestDto createCarBrandRequestDto) {
        return modelMapper.map(createCarBrandRequestDto, CarBrand.class);
    }

    public CarBrand toEntity(UpdateCarBrandRequestDto createCarBrandRequestDto) {
        return modelMapper.map(createCarBrandRequestDto, CarBrand.class);
    }

    public CarBrand updateFromEntity(CarBrand updatedEntity, CarBrand entity) {
        modelMapper.map(updatedEntity, entity);
        return updatedEntity;
    }

    public CarBrandResponseDto toDto(CarBrand carBrand) {
        return modelMapper.map(carBrand, CarBrandResponseDto.class);
    }
}
