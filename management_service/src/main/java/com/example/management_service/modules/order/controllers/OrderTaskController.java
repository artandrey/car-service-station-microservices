package com.example.management_service.modules.order.controllers;

import com.example.management_service.modules.order.dto.order_task.CreateOrderTaskRequestDto;
import com.example.management_service.modules.order.dto.order_task.OrderTaskResponseDto;
import com.example.management_service.modules.order.dto.order_task.UpdateOrderTaskRequestDto;
import com.example.management_service.modules.order.entities.OrderTask;
import com.example.management_service.modules.order.mappers.OrderTaskMapper;
import com.example.management_service.modules.order.services.order_task.IOrderTaskService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/order-tasks")
@Tag(name = "Order Tasks", description = "APIs to manage order tasks")
public class OrderTaskController {

    @Autowired
    private IOrderTaskService orderTaskService;

    @Autowired
    private OrderTaskMapper orderTaskMapper;

    @GetMapping
    @Operation(summary = "Get all order tasks", description = "Retrieve all order tasks")
    public ResponseEntity<List<OrderTaskResponseDto>> getAllOrderTasks() {
        List<OrderTask> orderTasks = orderTaskService.getAllOrderTasks();
        return ResponseEntity.ok(orderTaskMapper.toDto(orderTasks));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order task by ID", description = "Retrieve an order task by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order task found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Order task not found")
    })
    public ResponseEntity<OrderTaskResponseDto> getOrderTaskById(@PathVariable Long id) {
        OrderTask orderTask = orderTaskService.getOrderTaskById(id);
        return ResponseEntity.ok(orderTaskMapper.toDto(orderTask));
    }

    @GetMapping("/by-order/{orderId}")
    @Operation(summary = "Get order tasks by order ID", description = "Retrieve all order tasks associated with a specific order")
    public ResponseEntity<List<OrderTaskResponseDto>> getOrderTasksByOrderId(@PathVariable Long orderId) {
        List<OrderTask> orderTasks = orderTaskService.getOrderTasksByOrderId(orderId);
        return ResponseEntity.ok(orderTaskMapper.toDto(orderTasks));
    }

    @PostMapping
    @Operation(summary = "Create an order task", description = "Create a new order task")
    public ResponseEntity<OrderTaskResponseDto> createOrderTask(
            @Valid @RequestBody CreateOrderTaskRequestDto requestDto) {
        OrderTask orderTask = orderTaskService.createOrderTask(orderTaskMapper.toEntity(requestDto));
        return new ResponseEntity<>(orderTaskMapper.toDto(orderTask), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update an order task", description = "Update an existing order task")
    public ResponseEntity<OrderTaskResponseDto> updateOrderTask(@PathVariable Long id,
            @Valid @RequestBody UpdateOrderTaskRequestDto requestDto) {
        OrderTask updatedOrderTask = orderTaskService.updateOrderTask(id, orderTaskMapper.toEntity(requestDto));
        return ResponseEntity.ok(orderTaskMapper.toDto(updatedOrderTask));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order task", description = "Delete an existing order task")
    public ResponseEntity<Void> deleteOrderTask(@PathVariable Long id) {
        orderTaskService.deleteOrderTask(id);
        return ResponseEntity.noContent().build();
    }
}
