package com.example.payment_service.modules.bill.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.payment_service.modules.bill.dto.BillResponseDto;
import com.example.payment_service.modules.bill.entities.Bill;
import com.example.payment_service.modules.bill.exceptions.BillNotFoundException;
import com.example.payment_service.modules.bill.mappers.BillMapper;
import com.example.payment_service.modules.bill.services.bill.implementation.BillService;
import com.example.payment_service.shared.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/public/bills")
public class PublicBillController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillMapper billMapper;

    @Operation(summary = "Get all bills for a client", description = "Retrieve a list of all bills associated with the authenticated client.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BillResponseDto>> getAllBills(@RequestHeader("Authorization") String authorization) {
        List<Bill> bills = billService.getBillsByClientId(JwtUtil.mapJwtToUser(authorization).getSid());
        return ResponseEntity.ok(bills.stream().map(billMapper::toDto).toList());
    }

    @Operation(summary = "Get bill by order ID", description = "Retrieve a bill by its associated order ID for the authenticated client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of the bill"),
            @ApiResponse(responseCode = "404", description = "Bill not found for the provided order ID or unauthorized access")
    })
    @GetMapping(value = "/by-order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BillResponseDto> getBillByOrderId(@PathVariable Long orderId,
            @RequestHeader("Authorization") String authorization) {
        Bill bill = billService.getBillByOrderId(orderId);
        if (bill.getClientId() != JwtUtil.mapJwtToUser(authorization).getSid()) {
            throw new BillNotFoundException(orderId);
        }
        return ResponseEntity.ok(billMapper.toDto(bill));
    }
}
