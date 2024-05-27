package com.example.management_service.order;

import com.example.management_service.modules.order.entities.Order;
import com.example.management_service.modules.order.exceptions.OrderNotFoundException;
import com.example.management_service.modules.order.repository.OrderRepository;
import com.example.management_service.modules.order.services.order.implementation.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setId(1L);
        order.setClientId("client123");
    }

    @Test
    void testGetAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();

        assertEquals(orders, result);
    }

    @Test
    void testGetOrderById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order result = orderService.getOrderById(1L);

        assertEquals(order, result);
    }

    @Test
    void testGetOrderByIdNotFound() {
        when(orderRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(2L));
    }

    @Test
    void testCreateOrder() {
        when(orderRepository.save(order)).thenReturn(order);

        Order result = orderService.createOrder(order);

        assertEquals(order, result);
    }

    @Test
    void testUpdateOrder() {
        Order updatedOrder = new Order();
        updatedOrder.setId(1L);
        updatedOrder.setClientId("client456");

        when(orderRepository.existsById(1L)).thenReturn(true);
        when(orderRepository.save(updatedOrder)).thenReturn(updatedOrder);

        Order result = orderService.updateOrder(1L, updatedOrder);

        assertEquals(updatedOrder, result);
    }

    @Test
    void testUpdateOrderNotFound() {
        when(orderRepository.existsById(2L)).thenReturn(false);

        Order updatedOrder = new Order();
        updatedOrder.setId(2L);
        updatedOrder.setClientId("client456");

        assertThrows(OrderNotFoundException.class, () -> orderService.updateOrder(2L, updatedOrder));
    }

    @Test
    void testDeleteOrder() {
        when(orderRepository.existsById(1L)).thenReturn(true);

        boolean result = orderService.deleteOrder(1L);

        assertTrue(result);
        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteOrderNotFound() {
        when(orderRepository.existsById(2L)).thenReturn(false);

        assertThrows(OrderNotFoundException.class, () -> orderService.deleteOrder(2L));
    }

    @Test
    void testGetOrdersByClientId() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderRepository.getOrdersByClientId("client123")).thenReturn(orders);

        List<Order> result = orderService.getOrdersByClientId("client123");

        assertEquals(orders, result);
    }
}