package com.example.management_service.modules.car.services.car_brand.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.management_service.modules.car.entities.CarBrand;
import com.example.management_service.modules.car.exceptions.CarBrandNotFoundException;
import com.example.management_service.modules.car.repository.car_brand.CarBrandRepository;
import com.example.management_service.modules.car.services.car_brand.ICarBrandService;

@Service
public class CarBrandService implements ICarBrandService {
    private final CarBrandRepository carBrandRepository;

    public CarBrandService(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    @Override
    public List<CarBrand> getAllCarBrands() {
        return carBrandRepository.findAll();
    }

    @Override
    public CarBrand getCarBrandById(Long carBrandId) {
        return carBrandRepository.findById(carBrandId)
                .orElseThrow(() -> new CarBrandNotFoundException(carBrandId));
    }

    @Override
    public CarBrand createCarBrand(CarBrand carBrand) {
        return carBrandRepository.save(carBrand);
    }

    @Override
    public CarBrand updateCarBrand(CarBrand carBrand) {
        if (!carBrandRepository.existsById(carBrand.getId())) {
            throw new CarBrandNotFoundException(carBrand.getId());
        }
        return carBrandRepository.save(carBrand);
    }

    @Override
    public boolean deleteCarBrand(Long carBrandId) {
        if (!carBrandRepository.existsById(carBrandId)) {
            throw new CarBrandNotFoundException(carBrandId);
        }
        carBrandRepository.deleteById(carBrandId);
        return true;
    }
}
