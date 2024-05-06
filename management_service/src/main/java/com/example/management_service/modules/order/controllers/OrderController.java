package com.example.management_service.modules.order.controllers;

import com.example.management_service.modules.order.dto.CreateOrderRequestDto;
import com.example.management_service.modules.order.dto.OrderResponseDto;
import com.example.management_service.modules.order.dto.UpdateOrderRequestDto;
import com.example.management_service.modules.order.entities.Order;
import com.example.management_service.modules.order.mappers.OrderMapper;
import com.example.management_service.modules.order.services.IOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orderMapper.toDto(orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(orderMapper.toDto(order));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @Validated @RequestBody CreateOrderRequestDto requestDto) {
        Order order = orderService.createOrder(orderMapper.toEntity(requestDto));
        return new ResponseEntity<>(orderMapper.toDto(order), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long id,
            @Validated @RequestBody UpdateOrderRequestDto requestDto) {
        Order updatedOrder = orderService.updateOrder(id, orderMapper.toEntity(requestDto, id));
        return ResponseEntity.ok(orderMapper.toDto(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    // TO-DO add order status update
    @PostMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> updateStatus(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(orderMapper.toDto(order));
    }
}