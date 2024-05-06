package com.example.management_service.modules.car.mappers;

import com.example.management_service.modules.car.dto.car_model.CarModelResponseDto;
import com.example.management_service.modules.car.dto.car_model.CreateCarModelRequestDto;
import com.example.management_service.modules.car.dto.car_model.UpdateCarModelRequestDto;
import com.example.management_service.modules.car.entities.CarBrand;
import com.example.management_service.modules.car.entities.CarModel;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CarModelMapper {
    private final ModelMapper modelMapper;

    public CarModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.createTypeMap(CreateCarModelRequestDto.class, CarModel.class)
                .addMappings(map -> map.using(ConverterService.idToEntity(CarBrand::new))
                        .map(CreateCarModelRequestDto::getBrandId, CarModel::setCarBrand));
    }

    public CarModel createDtoToModel(CreateCarModelRequestDto createCarModelRequestDto) {
        return modelMapper.map(createCarModelRequestDto, CarModel.class);
    }

    public CarModel updateDtoToModel(UpdateCarModelRequestDto updateCarModelRequestDto, long id) {
        CarModel carModel = modelMapper.map(updateCarModelRequestDto, CarModel.class);
        carModel.setId(id);
        return carModel;
    }

    public CarModelResponseDto toDto(CarModel carModel) {
        return modelMapper.map(carModel, CarModelResponseDto.class);
    }

    public List<CarModelResponseDto> toDto(List<CarModel> carModels) {
        return carModels.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
