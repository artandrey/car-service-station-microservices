package com.example.payment_service.modules.bill.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.payment_service.modules.bill.dto.BillResponseDto;
import com.example.payment_service.modules.bill.entities.Bill;
import com.example.payment_service.modules.bill.exceptions.BillNotFoundException;
import com.example.payment_service.modules.bill.mappers.BillMapper;
import com.example.payment_service.modules.bill.services.implementation.BillService;
import com.example.payment_service.shared.util.JwtUtil;

@RestController
@RequestMapping("/bills/")
public class PublicBillController {
    @Autowired
    private BillService billService;

    @Autowired
    private BillMapper billMapper;

    @GetMapping()
    public ResponseEntity<List<BillResponseDto>> getAllBills(@RequestHeader("Authorization") String authorization) {
        List<Bill> bills = billService.getBillsByClientId(JwtUtil.mapJwtToUser(authorization).getSid());
        return ResponseEntity.ok(bills.stream().map(billMapper::toDto).toList());
    }

    @GetMapping("/by-order/{orderId}")
    public ResponseEntity<BillResponseDto> getBillByOrderId(@PathVariable Long orderId,
            @RequestHeader("Authorization") String authorization) {

        Bill bill = billService.getBillByOrderId(orderId);

        if (bill.getClientId() != JwtUtil.mapJwtToUser(authorization).getSid()) {
            throw new BillNotFoundException(orderId);
        }
        return ResponseEntity.ok(billMapper.toDto(bill));
    }

}