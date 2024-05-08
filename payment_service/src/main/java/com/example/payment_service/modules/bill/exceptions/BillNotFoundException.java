package com.example.payment_service.modules.bill.exceptions;

import com.example.payment_service.shared.exceptions.ClientException;

public class BillNotFoundException extends ClientException {
    public BillNotFoundException(Long billId) {
        super("BILL_NOT_FOUND", "Bill not found for order with ID: " + billId);
    }
}
