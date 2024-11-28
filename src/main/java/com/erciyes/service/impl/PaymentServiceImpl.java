package com.erciyes.service.impl;

import com.erciyes.dto.DtoPayment;
import com.erciyes.mapper.PaymentMapper;
import com.erciyes.model.Payment;
import com.erciyes.repository.PaymentRepository;
import com.erciyes.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    PaymentMapper paymentMapper;

    @Override
    public DtoPayment createPayment(Payment payment) {
        paymentRepository.save(payment);
        DtoPayment dtoPayment = paymentMapper.toDto(payment);
        return dtoPayment;
    }

    @Override
    public List<DtoPayment> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        List<DtoPayment> dtoPaymentList = new ArrayList<>();
        for (Payment payment : payments) {
            DtoPayment dtoPayment = paymentMapper.toDto(payment);
            dtoPaymentList.add(dtoPayment);
        }
        return dtoPaymentList;
    }

    @Override
    public DtoPayment getPaymentById(Long id) {
        Optional<Payment> optional = paymentRepository.findById(id);
        if (optional.isPresent()) {
            DtoPayment dtoPayment = paymentMapper.toDto(optional.get());
            return dtoPayment;
        }
        return null;
    }

    @Override
    public void deletePayment(Long id) {
        Optional<Payment> optional = paymentRepository.findById(id);
        if (optional.isPresent()) {
            paymentRepository.delete(optional.get());
        }
    }

    @Override
    public DtoPayment updatePayment(Long id, Payment payment) {
        Optional<Payment> optional = paymentRepository.findById(id);
        if (optional.isPresent()) {
            payment.setId(id);
            Payment dbPayment = paymentRepository.save(payment);
            return paymentMapper.toDto(dbPayment);
        }
        return null;
    }
}
