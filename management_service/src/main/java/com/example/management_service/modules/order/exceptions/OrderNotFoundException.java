package com.example.management_service.modules.order.exceptions;

import com.example.management_service.shared.exceptions.ClientException;

public class OrderNotFoundException extends ClientException {
    public OrderNotFoundException(Long orderId) {
        super("ORDER_NOT_FOUND", "Order not found with ID: " + orderId);
    }
}