package com.example.management_service.modules.car.services.car;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.example.management_service.modules.car.entities.Car;

public interface ICarService {

    public List<Car> getCars(PageRequest pageRequest);

    public List<Car> getOwnerCars(Long userId, PageRequest pageRequest);

    public Car createCar(Car car);

    public Car updateCar(Car car);

    public boolean deleteCar(Long carId);

    public Car getCarById(Long carId);
}