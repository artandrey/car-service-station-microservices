package com.example.management_service.modules.order.entities.order_task;

public interface ITaskState {
    void updateStatus(OrderTaskStatus newStatus);
}
