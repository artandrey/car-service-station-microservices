package com.example.management_service.modules.car.services.car.implementation;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.management_service.modules.car.entities.Car;
import com.example.management_service.modules.car.exceptions.CarNotFoundException;
import com.example.management_service.modules.car.mappers.CarMapper;
import com.example.management_service.modules.car.repository.car.CarRepository;
import com.example.management_service.modules.car.services.car.ICarService;
import com.example.management_service.modules.car.services.car_model.ICarModelService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarService implements ICarService {
    private final CarRepository carRepository;
    private final ICarModelService carModelService;
    private final CarMapper carMapper;

    @Override
    public List<Car> getCars(PageRequest pageRequest) {
        return carRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<Car> getOwnerCars(Long userId, PageRequest pageRequest) {
        return carRepository.findByOwnerId(userId, pageRequest);
    }

    @Override
    public Car createCar(Car car) {
        carModelService.getCarModelById(car.getCarModel().getId());
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car car) {
        Car carToUpdate = getCarById(car.getId());
        return carRepository.save(carMapper.updateFromEntity(carToUpdate, car));
    }

    @Override
    public boolean deleteCar(Long carId) {
        if (!carRepository.existsById(carId)) {
            throw new CarNotFoundException(carId);
        }
        carRepository.deleteById(carId);
        return true;
    }

    @Override
    public Car getCarById(Long carId) {
        if (!carRepository.existsById(carId)) {
            throw new CarNotFoundException(carId);
        }
        return carRepository.getReferenceById(carId);
    }
}
