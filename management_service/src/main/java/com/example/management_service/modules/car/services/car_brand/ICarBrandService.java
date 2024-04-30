package com.example.management_service.modules.car.services.car_brand;

import java.util.List;

import com.example.management_service.modules.car.entities.CarBrand;

public interface ICarBrandService {
    List<CarBrand> getAllCarBrands();

    CarBrand getCarBrandById(Long carBrandId);

    CarBrand createCarBrand(CarBrand carBrand);

    CarBrand updateCarBrand(CarBrand carBrand);

    boolean deleteCarBrand(Long carBrandId);
}
