package com.example.management_service.modules.car.entities;

import com.example.management_service.shared.entities.BaseEntity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "car_models")
public class CarModel extends BaseEntity {

    @OneToMany(mappedBy = "carModel", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private List<Car> cars;

    private String title;
    private Integer year;
}
