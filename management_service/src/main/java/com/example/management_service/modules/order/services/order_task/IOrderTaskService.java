package com.example.management_service.modules.order.services.order_task;

import com.example.management_service.modules.order.entities.OrderTask;
import com.example.management_service.modules.user.entities.WorkerProfile;

import java.util.List;

public interface IOrderTaskService {
    List<OrderTask> getAllOrderTasks();

    OrderTask getOrderTaskById(Long orderTaskId);

    OrderTask createOrderTask(OrderTask orderTask);

    OrderTask updateOrderTask(Long orderTaskId, OrderTask orderTask);

    boolean deleteOrderTask(Long orderTaskId);

    List<OrderTask> getOrderTasksByOrderId(Long orderId);

    OrderTask addWorker(Long orderTaskId, WorkerProfile workerProfile);
}
