package com.example.management_service.modules.car.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.management_service.modules.car.dto.car.CarResponseDto;
import com.example.management_service.modules.car.dto.car.CreateCarRequestDto;
import com.example.management_service.modules.car.dto.car.UpdateCarRequestDto;
import com.example.management_service.modules.car.entities.Car;
import com.example.management_service.modules.car.entities.CarModel;
import com.example.management_service.shared.services.ConverterService;

@Component
public class CarMapper {
    private final ModelMapper modelMapper;

    public CarMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        this.modelMapper.createTypeMap(CreateCarRequestDto.class, Car.class)
                .addMappings(map -> map.using(ConverterService.idToEntity(CarModel::new))
                        .map(CreateCarRequestDto::getCarModelId, Car::setCarModel));
        this.modelMapper.createTypeMap(Car.class, CarResponseDto.class)
                .addMappings(map -> map.map(src -> src.getCarModel().getId(), CarResponseDto::setCarModelId));
    }

    public Car toEntity(CreateCarRequestDto createCarRequestDto) {
        return modelMapper.map(createCarRequestDto, getModelClass());
    }

    public Car toEntity(UpdateCarRequestDto updateCarRequestDto, long id) {
        Car car = modelMapper.map(updateCarRequestDto, getModelClass());
        car.setId(id);
        return car;
    }

    public Car updateFromEntity(Car updatedEntity, Car entity) {
        modelMapper.map(updatedEntity, entity);
        return updatedEntity;
    }

    public CarResponseDto toDto(Car car) {
        return modelMapper.map(car, CarResponseDto.class);
    }

    private Class<Car> getModelClass() {
        return Car.class;
    }

}
