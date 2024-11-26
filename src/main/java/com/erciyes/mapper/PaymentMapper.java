package com.erciyes.mapper;

import com.erciyes.dto.DtoPayment;
import com.erciyes.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public DtoPayment toDto(Payment payment) {
        if (payment == null) return null;
        DtoPayment dtoPayment = new DtoPayment();
        dtoPayment.setAmount(payment.getAmount());
        return dtoPayment;
    }
}
