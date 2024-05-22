package com.example.payment_service.modules.bill.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.payment_service.modules.bill.dto.CreateCashPaymentRequestDto;
import com.example.payment_service.modules.bill.entities.Bill;
import com.example.payment_service.modules.bill.exceptions.BillNotFoundException;
import com.example.payment_service.modules.bill.exceptions.NotEnoughMoneyException;
import com.example.payment_service.modules.bill.repository.BillRepository;
import com.example.payment_service.modules.bill.services.IBillService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BillService implements IBillService {
    private final BillRepository billRepository;

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill getBillByOrderId(Long orderId) {
        return billRepository.findByOrderId(orderId);
    }

    @Transactional
    public Bill createCashPayment(CreateCashPaymentRequestDto requestDto) throws NotEnoughMoneyException {
        Bill bill = Optional.ofNullable(billRepository.findByOrderId(requestDto.getOrderId())).orElseThrow(
                () -> new BillNotFoundException(requestDto.getOrderId()));

        double paid = requestDto.getPaid();
        double rest = bill.getAmount() - paid;

        if (paid < bill.getAmount()) {
            throw new NotEnoughMoneyException();
        }

        bill.setPaid(paid);
        bill.setRest(rest);

        return billRepository.save(bill);
    }

    @Override
    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public List<Bill> getBillsByClientId(String clientId) {
        return billRepository.findByClientId(clientId);
    }
}