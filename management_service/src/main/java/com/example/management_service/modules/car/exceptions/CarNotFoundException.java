package com.example.management_service.modules.car.exceptions;

import com.example.management_service.shared.exceptions.ClientException;

public class CarNotFoundException extends ClientException {
    public CarNotFoundException(Long carId) {
        super("CAR_NOT_FOUND", "Car with ID " + carId + " not found");
    }

}
