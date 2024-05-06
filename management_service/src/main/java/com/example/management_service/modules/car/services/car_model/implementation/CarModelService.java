package com.example.management_service.modules.car.services.car_model.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.management_service.modules.car.entities.CarModel;
import com.example.management_service.modules.car.exceptions.CarModelNotFoundException;
import com.example.management_service.modules.car.repository.car_model.CarModelRepository;
import com.example.management_service.modules.car.services.car_model.ICarModelService;

@Service
public class CarModelService implements ICarModelService {
    private final CarModelRepository carModelRepository;

    public CarModelService(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

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
    public CarModel updateCarModel(CarModel carModel) {
        if (!carModelRepository.existsById(carModel.getId())) {
            throw new CarModelNotFoundException(carModel.getId());
        }
        return carModelRepository.save(carModel);
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
