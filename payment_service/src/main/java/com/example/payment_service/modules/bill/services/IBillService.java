package com.example.payment_service.modules.bill.services;

import java.util.List;

import com.example.payment_service.modules.bill.dto.CreateCashPaymentRequestDto;
import com.example.payment_service.modules.bill.entities.Bill;

public interface IBillService {

    Bill createBill(Bill bill);

    List<Bill> getAllBills();

    Bill getBillByOrderId(Long orderId);

    Bill createCashPayment(CreateCashPaymentRequestDto requestDto);

    List<Bill> getBillsByClientId(String clientId);
}
