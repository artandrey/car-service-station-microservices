package com.example.management_service.modules.car.services.car_part;

import java.util.List;

import com.example.management_service.modules.car.entities.CarPart;

public interface ICarPartService {
    List<CarPart> getAllCarParts();

    CarPart getCarPartById(Long carPartId);

    CarPart createCarPart(CarPart carPart);

    CarPart updateCarPart(Long carPartId, CarPart carPart);

    boolean deleteCarPart(Long carPartId);
}