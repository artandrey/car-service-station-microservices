package com.example.management_service.order;

import com.example.management_service.modules.order.entities.OrderTask;
import com.example.management_service.modules.order.exceptions.OrderTaskNotFoundException;
import com.example.management_service.modules.order.mappers.OrderTaskMapper;
import com.example.management_service.modules.order.repository.OrderTaskRepository;
import com.example.management_service.modules.order.services.order_task.implementation.OrderTaskService;
import com.example.management_service.modules.user.entities.WorkerProfile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderTaskServiceTest {

    @Mock
    private OrderTaskRepository orderTaskRepository;

    @Mock
    private OrderTaskMapper orderTaskMapper;

    @InjectMocks
    private OrderTaskService orderTaskService;

    private OrderTask orderTask;

    @BeforeEach
    void setUp() {
        orderTask = new OrderTask();
        orderTask.setId(1L);
    }

    @Test
    void testGetAllOrderTasks() {
        List<OrderTask> orderTasks = new ArrayList<>();
        orderTasks.add(orderTask);

        when(orderTaskRepository.findAll()).thenReturn(orderTasks);

        List<OrderTask> result = orderTaskService.getAllOrderTasks();

        assertEquals(orderTasks, result);
    }

    @Test
    void testGetOrderTaskById() {
        when(orderTaskRepository.findById(1L)).thenReturn(Optional.of(orderTask));

        OrderTask result = orderTaskService.getOrderTaskById(1L);

        assertEquals(orderTask, result);
    }

    @Test
    void testGetOrderTaskByIdNotFound() {
        when(orderTaskRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(OrderTaskNotFoundException.class, () -> orderTaskService.getOrderTaskById(2L));
    }

    @Test
    void testCreateOrderTask() {
        when(orderTaskRepository.save(orderTask)).thenReturn(orderTask);

        OrderTask result = orderTaskService.createOrderTask(orderTask);

        assertEquals(orderTask, result);
    }

    @Test
    void testUpdateOrderTask() {
        OrderTask updatedOrderTask = new OrderTask();
        updatedOrderTask.setId(1L);

        when(orderTaskRepository.findById(1L)).thenReturn(Optional.of(orderTask));
        when(orderTaskMapper.updateFromEntity(updatedOrderTask, orderTask)).thenReturn(updatedOrderTask);
        when(orderTaskRepository.save(updatedOrderTask)).thenReturn(updatedOrderTask);

        OrderTask result = orderTaskService.updateOrderTask(1L, updatedOrderTask);

        assertEquals(updatedOrderTask, result);
    }

    @Test
    void testUpdateOrderTaskNotFound() {
        when(orderTaskRepository.findById(2L)).thenReturn(Optional.empty());

        OrderTask updatedOrderTask = new OrderTask();
        updatedOrderTask.setId(2L);

        assertThrows(OrderTaskNotFoundException.class, () -> orderTaskService.updateOrderTask(2L, updatedOrderTask));
    }

    @Test
    void testDeleteOrderTask() {
        when(orderTaskRepository.existsById(1L)).thenReturn(true);

        boolean result = orderTaskService.deleteOrderTask(1L);

        assertTrue(result);
        verify(orderTaskRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteOrderTaskNotFound() {
        when(orderTaskRepository.existsById(2L)).thenReturn(false);

        assertThrows(OrderTaskNotFoundException.class, () -> orderTaskService.deleteOrderTask(2L));
    }

    @Test
    void testGetOrderTasksByOrderId() {
        List<OrderTask> orderTasks = new ArrayList<>();
        orderTasks.add(orderTask);

        when(orderTaskRepository.findByOrderId(1L)).thenReturn(orderTasks);

        List<OrderTask> result = orderTaskService.getOrderTasksByOrderId(1L);

        assertEquals(orderTasks, result);
    }

    @Test
    void testAddWorker() {
        WorkerProfile workerProfile = new WorkerProfile();
        workerProfile.setId(1L);

        Set<WorkerProfile> workers = new HashSet<WorkerProfile>();
        workers.add(workerProfile);

        OrderTask updatedOrderTask = new OrderTask();
        updatedOrderTask.setId(1L);
        updatedOrderTask.setAssignedTo(workers);

        when(orderTaskRepository.findById(1L)).thenReturn(Optional.of(orderTask));
        when(orderTaskMapper.updateFromEntity(orderTask, orderTask)).thenReturn(updatedOrderTask);
        when(orderTaskRepository.save(orderTask)).thenReturn(updatedOrderTask);

        OrderTask result = orderTaskService.addWorker(1L, workerProfile);

        assertEquals(1, result.getAssignedTo().size());
        assertTrue(result.getAssignedTo().contains(workerProfile));
        verify(orderTaskRepository, times(1)).save(updatedOrderTask);
    }
}
