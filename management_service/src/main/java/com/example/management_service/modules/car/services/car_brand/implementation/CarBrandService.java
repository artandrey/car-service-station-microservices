package com.example.management_service.modules.car.services.car_brand.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.management_service.modules.car.entities.CarBrand;
import com.example.management_service.modules.car.exceptions.CarBrandNotFoundException;
import com.example.management_service.modules.car.mappers.CarBrandMapper;
import com.example.management_service.modules.car.repository.car_brand.CarBrandRepository;
import com.example.management_service.modules.car.services.car_brand.ICarBrandService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarBrandService implements ICarBrandService {
    private final CarBrandRepository carBrandRepository;
    private final CarBrandMapper carBrandMapper;

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
    public CarBrand updateCarBrand(Long carBrandId, CarBrand carBrand) {
        CarBrand carBrandToUpdate = getCarBrandById(carBrandId);
        return carBrandRepository.save(carBrandMapper.updateFromEntity(carBrand, carBrandToUpdate));

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
