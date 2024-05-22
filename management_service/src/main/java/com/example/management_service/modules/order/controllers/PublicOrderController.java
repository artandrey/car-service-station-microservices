package com.example.management_service.modules.order.controllers;

import com.example.management_service.modules.order.dto.order.OrderResponseDto;
import com.example.management_service.modules.order.entities.Order;
import com.example.management_service.modules.order.exceptions.OrderNotFoundException;
import com.example.management_service.modules.order.mappers.OrderMapper;
import com.example.management_service.modules.order.services.order.IOrderService;
import com.example.management_service.shared.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class PublicOrderController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(@RequestHeader("Authorization") String authorization) {
        List<Order> orders = orderService.getOrdersByClientId(JwtUtil.mapJwtToUser(authorization).getSid());
        return ResponseEntity.ok(orderMapper.toDto(orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id,
            @RequestHeader("Authorization") String authorization) {
        Order order = orderService.getOrderById(id);
        if (order.getClientId() != JwtUtil.mapJwtToUser(authorization).getSid()) {
            throw new OrderNotFoundException(id);
        }
        return ResponseEntity.ok(orderMapper.toDto(order));
    }

}