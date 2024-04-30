package com.example.management_service.modules.car.repository.car_brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.management_service.modules.car.entities.CarBrand;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {
}