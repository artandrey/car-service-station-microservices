package com.example.management_service.modules.order.controllers;

import com.example.management_service.modules.order.dto.order.OrderResponseDto;
import com.example.management_service.modules.order.entities.Order;
import com.example.management_service.modules.order.exceptions.OrderNotFoundException;
import com.example.management_service.modules.order.mappers.OrderMapper;
import com.example.management_service.modules.order.services.order.IOrderService;
import com.example.management_service.shared.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/orders")
@Tag(name = "Public Orders", description = "APIs to retrieve public orders")
public class PublicOrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping
    @Operation(summary = "Get all orders for the authenticated client", description = "Retrieve all orders for the authenticated client")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(@RequestHeader("Authorization") String authorization) {
        List<Order> orders = orderService.getOrdersByClientId(JwtUtil.mapJwtToUser(authorization).getSid());
        return ResponseEntity.ok(orderMapper.toDto(orders));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID for the authenticated client", description = "Retrieve an order by its ID for the authenticated client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id,
            @RequestHeader("Authorization") String authorization) {
        Order order = orderService.getOrderById(id);
        if (order.getClientId() != JwtUtil.mapJwtToUser(authorization).getSid()) {
            throw new OrderNotFoundException(id);
        }
        return ResponseEntity.ok(orderMapper.toDto(order));
    }
}
