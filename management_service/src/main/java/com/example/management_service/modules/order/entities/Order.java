package com.example.management_service.modules.order.entities;

import com.example.management_service.modules.car.entities.Car;
import com.example.management_service.shared.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.List;

@Table(name = "orders")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<OrderTask> orderTasks;

    @ManyToOne(targetEntity = Car.class)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @Column(name = "client_id")
    private String clientId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "completed_at")
    private Date completedAt;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private CompletionStatus orderStatus;
}