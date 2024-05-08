package com.example.management_service.modules.order.repository;

import com.example.management_service.modules.order.entities.OrderTask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTaskRepository extends JpaRepository<OrderTask, Long> {
    List<OrderTask> findByOrderId(Long orderId);
}