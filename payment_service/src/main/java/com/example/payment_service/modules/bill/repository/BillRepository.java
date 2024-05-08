package com.example.payment_service.modules.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payment_service.modules.bill.entities.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    Bill findByOrderId(Long orderId);
}