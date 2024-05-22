package com.example.management_service.modules.order.controllers;

import com.example.management_service.modules.order.dto.order.CreateOrderRequestDto;
import com.example.management_service.modules.order.dto.order.OrderResponseDto;
import com.example.management_service.modules.order.dto.order.UpdateOrderRequestDto;
import com.example.management_service.modules.order.entities.CompletionStatus;
import com.example.management_service.modules.order.entities.Order;
import com.example.management_service.modules.order.mappers.OrderMapper;
import com.example.management_service.modules.order.services.order.IOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/manager")
@Tag(name = "Order Management", description = "APIs to manage orders by managers")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping
    @Operation(summary = "Get all orders", description = "Retrieve all orders")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orderMapper.toDto(orders));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Retrieve an order by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(orderMapper.toDto(order));
    }

    @PostMapping
    @Operation(summary = "Create an order", description = "Create a new order")
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody CreateOrderRequestDto requestDto) {
        Order order = orderService.createOrder(orderMapper.toEntity(requestDto));
        return new ResponseEntity<>(orderMapper.toDto(order), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an order", description = "Update an existing order")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long id,
            @Valid @RequestBody UpdateOrderRequestDto requestDto) {
        Order updatedOrder = orderService.updateOrder(id, orderMapper.toEntity(requestDto, id));
        return ResponseEntity.ok(orderMapper.toDto(updatedOrder));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order", description = "Delete an existing order")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/status/{status}")
    @Operation(summary = "Update order status", description = "Update the completion status of an order")
    public ResponseEntity<OrderResponseDto> updateStatus(@PathVariable Long id, @PathVariable CompletionStatus status) {
        Order order = orderService.getOrderById(id);
        order.setOrderStatus(status);
        Order updatedOrder = orderService.updateOrder(id, order);
        return ResponseEntity.ok(orderMapper.toDto(updatedOrder));
    }
}
