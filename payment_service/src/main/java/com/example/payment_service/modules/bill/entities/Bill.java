package com.example.payment_service.modules.bill.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.util.Date;

import com.example.payment_service.shared.entities.BaseEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Bill extends BaseEntity {
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "amount")
    private double amount;
    @Column(name = "paid")
    private double paid;
    @Column(name = "rest")
    private double rest;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "warranty_expires_at")
    private Date warrantyExpiresAt;
}