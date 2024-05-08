package com.example.management_service.modules.car.entities;

import java.util.Set;

import com.example.management_service.shared.entities.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "car_brands")
public class CarBrand extends BaseEntity {
    private String title;
    @OneToMany(mappedBy = "carBrand")
    private Set<CarModel> carModels;
}
