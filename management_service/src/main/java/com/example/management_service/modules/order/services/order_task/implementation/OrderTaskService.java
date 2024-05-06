package com.example.management_service.modules.order.services.order_task.implementation;

import com.example.management_service.modules.order.entities.OrderTask;
import com.example.management_service.modules.order.exceptions.OrderTaskNotFoundException;
import com.example.management_service.modules.order.mappers.OrderTaskMapper;
import com.example.management_service.modules.order.repository.OrderTaskRepository;
import com.example.management_service.modules.order.services.order_task.IOrderTaskService;
import com.example.management_service.modules.user.entities.WorkerProfile;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderTaskService implements IOrderTaskService {
    private OrderTaskRepository orderTaskRepository;
    private OrderTaskMapper orderTaskMapper;

    @Override
    public List<OrderTask> getAllOrderTasks() {
        return orderTaskRepository.findAll();
    }

    @Override
    public OrderTask getOrderTaskById(Long orderTaskId) {
        return orderTaskRepository.findById(orderTaskId)
                .orElseThrow(() -> new OrderTaskNotFoundException(orderTaskId));
    }

    @Override
    public OrderTask createOrderTask(OrderTask orderTask) {
        return orderTaskRepository.save(orderTask);
    }

    @Override
    public OrderTask updateOrderTask(Long orderTaskId, OrderTask orderTask) {
        OrderTask orderTaskToUpdate = getOrderTaskById(orderTaskId);
        return orderTaskRepository.save(orderTaskMapper.updateFromEntity(orderTask, orderTaskToUpdate));
    }

    @Override
    public boolean deleteOrderTask(Long orderTaskId) {
        if (!orderTaskRepository.existsById(orderTaskId)) {
            throw new OrderTaskNotFoundException(orderTaskId);
        }
        orderTaskRepository.deleteById(orderTaskId);
        return true;
    }

    @Override
    public OrderTask addWorker(Long orderTaskId, WorkerProfile workerProfile) {
        OrderTask orderTask = getOrderTaskById(orderTaskId);
        orderTask.getAssignedTo().add(workerProfile);
        OrderTask updatedOrderTask = updateOrderTask(orderTaskId, orderTask);
        return updatedOrderTask;
    }
}