package com.example.payment_service.modules.bill.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;

import java.util.Calendar;
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
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "amount")
    private double amount = 0;
    @Column(name = "paid")
    private double paid = 0;
    @Column(name = "rest")
    private double rest = 0;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "warranty_expires_at")
    private Date warrantyExpiresAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(createdAt);
        cal.add(Calendar.DAY_OF_MONTH, 14);
        warrantyExpiresAt = cal.getTime();
    }
}