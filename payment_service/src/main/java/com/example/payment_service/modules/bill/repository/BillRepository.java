package com.example.payment_service.modules.bill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payment_service.modules.bill.entities.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    Bill findByOrderId(Long orderId);

    List<Bill> findByClientId(String clientId);
}