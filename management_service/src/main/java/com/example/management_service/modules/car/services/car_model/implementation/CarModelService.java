package com.example.management_service.modules.car.services.car_model.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.management_service.modules.car.entities.CarModel;
import com.example.management_service.modules.car.exceptions.CarModelNotFoundException;
import com.example.management_service.modules.car.mappers.CarModelMapper;
import com.example.management_service.modules.car.repository.car_model.CarModelRepository;
import com.example.management_service.modules.car.services.car_model.ICarModelService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarModelService implements ICarModelService {
    private final CarModelRepository carModelRepository;
    private final CarModelMapper carModelMapper;

    @Override
    public List<CarModel> getAllCarModels() {
        return carModelRepository.findAll();
    }

    @Override
    public CarModel getCarModelById(Long carModelId) {
        return carModelRepository.findById(carModelId)
                .orElseThrow(() -> new CarModelNotFoundException(carModelId));
    }

    @Override
    public CarModel createCarModel(CarModel carModel) {
        return carModelRepository.save(carModel);
    }

    @Override
    public CarModel updateCarModel(Long carModelId, CarModel carModel) {
        CarModel carModelToUpdate = getCarModelById(carModelId);

        return carModelRepository.save(carModelMapper.updateFromEntity(carModel, carModelToUpdate));
    }

    @Override
    public boolean deleteCarModel(Long carModelId) {
        if (!carModelRepository.existsById(carModelId)) {
            throw new CarModelNotFoundException(carModelId);
        }
        carModelRepository.deleteById(carModelId);
        return true;
    }
}
