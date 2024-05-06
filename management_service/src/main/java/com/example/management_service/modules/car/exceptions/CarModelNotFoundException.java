package com.example.management_service.modules.car.exceptions;

import com.example.management_service.shared.exceptions.ClientException;

public class CarModelNotFoundException extends ClientException {
    public CarModelNotFoundException(Long carModelId) {
        super("CAR_MODEL_NOT_FOUND", "CarModel not found with ID: " + carModelId);
    }
}
