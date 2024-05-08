package com.example.management_service.modules.car.entities;

import com.example.management_service.shared.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class CarPart extends BaseEntity {

    @ManyToMany(mappedBy = "carParts")
    private Set<CarModel> carModels;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private double price;

}