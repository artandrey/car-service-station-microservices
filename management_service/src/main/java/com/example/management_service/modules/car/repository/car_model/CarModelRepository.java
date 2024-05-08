package com.example.management_service.modules.car.repository.car_model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.management_service.modules.car.entities.CarModel;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {
}