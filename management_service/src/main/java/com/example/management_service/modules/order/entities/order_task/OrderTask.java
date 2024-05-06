package com.example.management_service.modules.order.entities.order_task;

import java.util.Date;
import java.util.Set;

import com.example.management_service.modules.car.entities.CarPart;
import com.example.management_service.modules.order.entities.Order;
import com.example.management_service.modules.user.entities.WorkerProfile;
import com.example.management_service.shared.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OrderTask extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderTaskStatus status;

    @Column(name = "work_price")
    private double workPrice;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "started_at")
    private Date startedAt;

    @Column(name = "completed_at")
    private Date completedAt;

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(name = "order_task_car_part", joinColumns = @JoinColumn(name = "order_task_id"), inverseJoinColumns = @JoinColumn(name = "car_part_id"))
    private Set<CarPart> usedParts;

    @ManyToMany
    @JoinTable(name = "order_task_worker_profile", joinColumns = @JoinColumn(name = "order_task_id"), inverseJoinColumns = @JoinColumn(name = "worker_profile_id"))
    private Set<WorkerProfile> assignedTo;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public void setState(ITaskState state) {
    }

}
