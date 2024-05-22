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
import com.example.payment_service.modules.bill.services.bill.implementation.BillService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/bills/management")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillMapper billMapper;

    @Operation(summary = "Get all bills", description = "Retrieve a list of all bills.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BillResponseDto>> getAllBills() {
        List<Bill> bills = billService.getAllBills();
        return ResponseEntity.ok(bills.stream().map(billMapper::toDto).toList());
    }

    @Operation(summary = "Create a bill", description = "Create a new bill.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BillResponseDto> createBill(@Valid @RequestBody CreateBillRequestDto createBillRequestDto) {
        Bill bill = billMapper.toDomain(createBillRequestDto);
        Bill createdBill = billService.createBill(bill);
        return ResponseEntity.ok(billMapper.toDto(createdBill));
    }

    @Operation(summary = "Get bill by order ID", description = "Retrieve a bill by its associated order ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of the bill"),
            @ApiResponse(responseCode = "404", description = "Bill not found for the provided order ID")
    })
    @GetMapping(value = "/by-order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BillResponseDto> getBillByOrderId(@PathVariable Long orderId) {
        Bill bill = billService.getBillByOrderId(orderId);
        return ResponseEntity.ok(billMapper.toDto(bill));
    }

    @Operation(summary = "Create a cash payment", description = "Record a cash payment for a bill.")
    @PostMapping(value = "/cash-payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BillResponseDto> createCashPayment(
            @Valid @RequestBody CreateCashPaymentRequestDto requestDto) {
        Bill bill = billService.createCashPayment(requestDto);
        return new ResponseEntity<>(billMapper.toDto(bill), HttpStatus.CREATED);
    }
}
