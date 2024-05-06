package com.example.management_service.modules.order.exceptions;

import com.example.management_service.shared.exceptions.ClientException;

public class OrderTaskNotFoundException extends ClientException {
    public OrderTaskNotFoundException(Long orderTaskId) {
        super("ORDER_TASK_NOT_FOUND", "Order Task not found with ID: " + orderTaskId);
    }
}