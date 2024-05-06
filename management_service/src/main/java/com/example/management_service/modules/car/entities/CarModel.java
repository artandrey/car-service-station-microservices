package com.example.management_service.modules.car.entities;

import com.example.management_service.shared.entities.BaseEntity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    @Column(name = "title")
    private String title;
    @Column(name = "year")
    private Integer year;

    @ManyToMany
    @JoinTable(name = "car_model_car_part", joinColumns = @JoinColumn(name = "car_model_id"), inverseJoinColumns = @JoinColumn(name = "car_part_id"))
    private Set<CarPart> carParts;

    @ManyToOne(targetEntity = CarBrand.class)
    @JoinColumn(name = "car_brand_id", referencedColumnName = "id")
    private CarBrand carBrand;
}
