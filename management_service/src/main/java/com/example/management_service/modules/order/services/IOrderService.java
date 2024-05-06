package com.example.management_service.modules.order.services;

import com.example.management_service.modules.order.entities.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAllOrders();

    Order getOrderById(Long orderId);

    Order createOrder(Order order);

    Order updateOrder(Long orderId, Order order);

    boolean deleteOrder(Long orderId);
}