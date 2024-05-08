package com.example.payment_service.modules.bill.exceptions;

import com.example.payment_service.shared.exceptions.ClientException;

public class NotEnoughMoneyException extends ClientException {
    public NotEnoughMoneyException() {
        super("NOT_ENOUGH_MONEY", "Not enough money paid for the bill");
    }
}