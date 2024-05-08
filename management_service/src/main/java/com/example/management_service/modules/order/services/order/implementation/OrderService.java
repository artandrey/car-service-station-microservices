package com.example.management_service.modules.order.services.order.implementation;

import com.example.management_service.modules.order.entities.Order;
import com.example.management_service.modules.order.exceptions.OrderNotFoundException;
import com.example.management_service.modules.order.repository.OrderRepository;
import com.example.management_service.modules.order.services.order.IOrderService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderId, Order order) {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderNotFoundException(orderId);
        }
        order.setId(orderId);
        return orderRepository.save(order);
    }

    @Override
    public boolean deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderNotFoundException(orderId);
        }
        orderRepository.deleteById(orderId);
        return true;
    }
}