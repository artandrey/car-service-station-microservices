package com.example.management_service.modules.order.entities;

import com.example.management_service.modules.car.entities.CarPart;
import com.example.management_service.modules.user.entities.WorkerProfile;
import com.example.management_service.shared.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OrderTask extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private CompletionStatus taskStatus;

    @Column(name = "work_price")
    private Double workPrice;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "started_at")
    private Date startedAt;

    @Column(name = "completed_at")
    private Date completedAt;

    @Column(name = "title")
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_task_car_part", joinColumns = @JoinColumn(name = "order_task_id"), inverseJoinColumns = @JoinColumn(name = "car_part_id"))
    private Set<CarPart> usedParts = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_task_worker_profile", joinColumns = @JoinColumn(name = "order_task_id"), inverseJoinColumns = @JoinColumn(name = "worker_profile_id"))
    private Set<WorkerProfile> assignedTo = new HashSet<>();

    @ManyToOne(targetEntity = Order.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

}