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

import java.util.List;

@RestController
@RequestMapping("/api/order-tasks")
public class OrderTaskController {
    @Autowired
    private IOrderTaskService orderTaskService;

    @Autowired
    private OrderTaskMapper orderTaskMapper;

    @GetMapping
    public ResponseEntity<List<OrderTaskResponseDto>> getAllOrderTasks() {
        List<OrderTask> orderTasks = orderTaskService.getAllOrderTasks();
        return ResponseEntity.ok(orderTaskMapper.toDto(orderTasks));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderTaskResponseDto> getOrderTaskById(@PathVariable Long id) {
        OrderTask orderTask = orderTaskService.getOrderTaskById(id);
        return ResponseEntity.ok(orderTaskMapper.toDto(orderTask));
    }

    @PostMapping
    public ResponseEntity<OrderTaskResponseDto> createOrderTask(
            @Valid @RequestBody CreateOrderTaskRequestDto requestDto) {
        OrderTask orderTask = orderTaskService.createOrderTask(orderTaskMapper.toEntity(requestDto));
        return new ResponseEntity<>(orderTaskMapper.toDto(orderTask), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderTaskResponseDto> updateOrderTask(@PathVariable Long id,
            @Valid @RequestBody UpdateOrderTaskRequestDto requestDto) {

        OrderTask updatedOrderTask = orderTaskService.updateOrderTask(id, orderTaskMapper.toEntity(requestDto));
        return ResponseEntity.ok(orderTaskMapper.toDto(updatedOrderTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderTask(@PathVariable Long id

    ) {
        orderTaskService.deleteOrderTask(id);
        return ResponseEntity.noContent().build();
    }
}