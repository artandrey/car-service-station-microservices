package com.example.management_service.modules.car.exceptions;

import com.example.management_service.shared.exceptions.ClientException;

public class CarPartNotFoundException extends ClientException {
    public CarPartNotFoundException(Long carPartId) {
        super("CAR_PART_NOT_FOUND", "Car Part not found with ID: " + carPartId);
    }
}