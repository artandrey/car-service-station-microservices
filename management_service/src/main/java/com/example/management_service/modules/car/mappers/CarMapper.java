package com.example.management_service.modules.car.mappers;

import java.util.function.Supplier;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.management_service.modules.car.dto.car.CarResponseDto;
import com.example.management_service.modules.car.dto.car.CreateCarRequestDto;
import com.example.management_service.modules.car.dto.car.UpdateCarRequestDto;
import com.example.management_service.modules.car.entities.Car;
import com.example.management_service.modules.car.entities.CarModel;
import com.example.management_service.shared.entities.BaseEntity;

@Component
public class CarMapper {
    private final ModelMapper modelMapper;

    public CarMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        this.modelMapper.createTypeMap(CreateCarRequestDto.class, Car.class)
                .addMappings(map -> map.using(ConverterService.idToEntity(CarModel::new))
                        .map(CreateCarRequestDto::getCarModelId, Car::setCarModel));
    }

    public Car createDtoToModel(CreateCarRequestDto createCarRequestDto) {
        return modelMapper.map(createCarRequestDto, getModelClass());
    }

    public Car updateDtoToModel(UpdateCarRequestDto updateCarRequestDto, long id) {
        Car car = modelMapper.map(updateCarRequestDto, getModelClass());
        car.setId(id);
        return car;
    }

    public CarResponseDto toDto(Car car) {
        return modelMapper.map(car, CarResponseDto.class);
    }

    private Class<Car> getModelClass() {
        return Car.class;
    }

}

class ConverterService {
    public static <ENTITY extends BaseEntity> Converter<Long, ENTITY> idToEntity(Supplier<ENTITY> entitySupplier) {
        return mappingContext -> {
            Long id = mappingContext.getSource();
            ENTITY entity = entitySupplier.get();
            if (id != null) {
                entity.setId(id);
                return entity;
            }
            return null;
        };
    }
}