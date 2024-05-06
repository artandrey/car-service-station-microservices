package com.example.management_service.modules.order.entities.order_task;

import java.util.Date;

public class PendingState implements ITaskState {
    private final OrderTask orderTask;

    public PendingState(OrderTask orderTask) {
        this.orderTask = orderTask;
    }

    @Override
    public void updateStatus(OrderTaskStatus newStatus) {
        if (newStatus == OrderTaskStatus.IN_PROGRESS) {
            orderTask.setState(new InProgressState(orderTask));
            orderTask.setStartedAt(new Date());
        } else if (newStatus == OrderTaskStatus.DONE) {
            orderTask.setState(new DoneState(orderTask));
            orderTask.setCompletedAt(new Date());
        }
    }
}