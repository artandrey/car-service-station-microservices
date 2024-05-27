package com.example.management_service.modules.car.entities;

import com.example.management_service.shared.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CarPart extends BaseEntity {

    @ManyToMany(mappedBy = "carParts")
    private Set<CarModel> carModels;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private double price;

}