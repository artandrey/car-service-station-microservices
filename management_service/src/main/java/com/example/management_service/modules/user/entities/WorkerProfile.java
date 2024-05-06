package com.example.management_service.modules.user.entities;

import java.util.Date;

import com.example.management_service.shared.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class WorkerProfile extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private WorkerPosition position;

    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "fire_date")
    private Date fireDate;

    @Column(name = "salary")
    private double salary;

}
