package com.example.management_service.modules.car.services.car_model;

import java.util.List;

import com.example.management_service.modules.car.entities.CarModel;

public interface ICarModelService {
    List<CarModel> getAllCarModels();

    CarModel getCarModelById(Long carModelId);

    CarModel createCarModel(CarModel carModel);

    CarModel updateCarModel(Long carModelId, CarModel carModel);

    boolean deleteCarModel(Long carModelId);
}
