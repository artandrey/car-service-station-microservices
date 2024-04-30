package com.example.management_service.modules.car.entities;

import com.example.management_service.shared.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Car extends BaseEntity {
    @ManyToOne(targetEntity = CarModel.class)
    @JoinColumn(name = "modelId", referencedColumnName = "id")
    private CarModel carModel;

    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "vin_code")
    private String vinCode;
    @Column(name = "color")
    private String color;
}
