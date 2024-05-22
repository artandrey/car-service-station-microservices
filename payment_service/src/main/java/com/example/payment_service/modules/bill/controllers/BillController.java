package com.example.payment_service.modules.bill.controllers;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.payment_service.modules.bill.dto.BillResponseDto;
import com.example.payment_service.modules.bill.dto.CreateBillRequestDto;
import com.example.payment_service.modules.bill.dto.CreateCashPaymentRequestDto;
import com.example.payment_service.modules.bill.entities.Bill;
import com.example.payment_service.modules.bill.mappers.BillMapper;
import com.example.payment_service.modules.bill.services.implementation.BillService;

@RestController
@RequestMapping("/bills/management")
public class BillController {
    @Autowired
    private BillService billService;

    @Autowired
    private BillMapper billMapper;

    @GetMapping()
    public ResponseEntity<List<BillResponseDto>> getAllBills() {
        List<Bill> bills = billService.getAllBills();
        return ResponseEntity.ok(bills.stream().map(billMapper::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<BillResponseDto> crateBill(@Valid @RequestBody CreateBillRequestDto createBillRequestDto) {
        Bill bill = billMapper.toDomain(createBillRequestDto);
        Bill createdBill = billService.createBill(bill);
        return ResponseEntity.ok(billMapper.toDto(createdBill));
    }

    @GetMapping("/by-order/{orderId}")
    public ResponseEntity<BillResponseDto> getBillByOrderId(@PathVariable Long orderId) {
        Bill bill = billService.getBillByOrderId(orderId);
        return ResponseEntity.ok(billMapper.toDto(bill));
    }

    @PostMapping("/cash-payment")
    public ResponseEntity<BillResponseDto> createCashPayment(
            @Valid @RequestBody CreateCashPaymentRequestDto requestDto) {
        Bill bill = billService.createCashPayment(requestDto);
        return new ResponseEntity<>(billMapper.toDto(bill), HttpStatus.CREATED);
    }
}