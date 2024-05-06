package com.example.management_service.modules.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.management_service.modules.car.entities.CarPart;

@Repository
public interface CarPartRepository extends JpaRepository<CarPart, Long> {
}