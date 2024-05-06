package com.example.management_service.modules.order.entities.order_task;

import java.util.Date;

public class InProgressState implements ITaskState {
    private final OrderTask orderTask;

    public InProgressState(OrderTask orderTask) {
        this.orderTask = orderTask;
    }

    @Override
    public void updateStatus(OrderTaskStatus newStatus) {
        if (newStatus == OrderTaskStatus.DONE) {
            orderTask.setState(new DoneState(orderTask));
            orderTask.setCompletedAt(new Date());
        }
    }
}