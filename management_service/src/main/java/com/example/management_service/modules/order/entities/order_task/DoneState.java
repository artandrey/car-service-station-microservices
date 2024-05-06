package com.example.management_service.modules.order.entities.order_task;

public class DoneState implements ITaskState {
    @SuppressWarnings("unused")
    private final OrderTask orderTask;

    public DoneState(OrderTask orderTask) {
        this.orderTask = orderTask;
    }

    @Override
    public void updateStatus(OrderTaskStatus newStatus) {
    }
}