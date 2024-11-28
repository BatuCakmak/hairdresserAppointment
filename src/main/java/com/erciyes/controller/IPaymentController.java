package com.erciyes.controller;

import com.erciyes.dto.DtoPayment;
import com.erciyes.model.Payment;

import java.util.List;

public interface IPaymentController {

    public DtoPayment createPayment(Payment payment);
    public List<DtoPayment> getAllPayments();
    public DtoPayment getPaymentById(Long id);
    public void deletePayment(Long id);
    public DtoPayment updatePayment(Long id, Payment payment);
}
