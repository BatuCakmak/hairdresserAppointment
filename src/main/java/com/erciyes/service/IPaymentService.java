package com.erciyes.service;

import com.erciyes.dto.DtoPayment;
import com.erciyes.dto.PaymentRequest;
import com.erciyes.model.Payment;

import java.util.List;

public interface IPaymentService {

    public DtoPayment createPayment(Payment payment);
    public List<DtoPayment> getAllPayments();
    public DtoPayment getPaymentById(Long id);
    public void deletePayment(Long id);
    public DtoPayment updatePayment(Long id, Payment payment);
    public com.iyzipay.model.Payment makePayment(PaymentRequest paymentRequest);

}
