package com.example.management_service.modules.car.exceptions;

import com.example.management_service.shared.exceptions.ClientException;

public class CarBrandNotFoundException extends ClientException {
    public CarBrandNotFoundException(Long carBrandId) {
        super("CAR_BRAND_NOT_FOUND", "CarBrand not found with ID: " + carBrandId);
    }
}