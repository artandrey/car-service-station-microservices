package com.example.management_service.modules.car.services.car_part.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.management_service.modules.car.entities.CarPart;
import com.example.management_service.modules.car.exceptions.CarPartNotFoundException;
import com.example.management_service.modules.car.mappers.CarPartMapper;
import com.example.management_service.modules.car.repository.CarPartRepository;
import com.example.management_service.modules.car.services.car_part.ICarPartService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarPartService implements ICarPartService {
    private final CarPartRepository carPartRepository;
    private final CarPartMapper carPartMapper;

    @Override
    public List<CarPart> getAllCarParts() {
        return carPartRepository.findAll();
    }

    @Override
    public CarPart getCarPartById(Long carPartId) {
        return carPartRepository.findById(carPartId)
                .orElseThrow(() -> new CarPartNotFoundException(carPartId));
    }

    @Override
    public CarPart createCarPart(CarPart carPart) {
        return carPartRepository.save(carPart);
    }

    @Override
    public CarPart updateCarPart(Long carPartId, CarPart carPart) {
        CarPart carPartToUpdate = getCarPartById(carPartId);
        return carPartRepository.save(carPartMapper.updateFromEntity(carPart, carPartToUpdate));
    }

    @Override
    public boolean deleteCarPart(Long carPartId) {
        if (!carPartRepository.existsById(carPartId)) {
            throw new CarPartNotFoundException(carPartId);
        }
        carPartRepository.deleteById(carPartId);
        return true;
    }
}