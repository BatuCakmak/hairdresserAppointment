package com.erciyes.service.impl;

import com.erciyes.config.IyzicoConfig;
import com.erciyes.dto.DtoPayment;
import com.erciyes.dto.PaymentRequest;
import com.erciyes.mapper.PaymentMapper;
import com.erciyes.model.Payment;
import com.erciyes.repository.PaymentRepository;
import com.erciyes.service.IPaymentService;
import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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


    @Override
    public com.iyzipay.model.Payment makePayment(PaymentRequest paymentRequest) {
        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setLocale(Locale.TR.getValue());
        request.setConversationId("123456789");//orderId
        request.setPrice(paymentRequest.getPrice());
        request.setPaidPrice(paymentRequest.getPrice());
        request.setCurrency(Currency.TRY.name());
        request.setInstallment(1);

        // Kullanıcı bilgilerini ayarla
        Buyer buyer = new Buyer();
        buyer.setId("BY789");
        buyer.setName(paymentRequest.getBuyerName());
        buyer.setSurname(paymentRequest.getBuyerSurname());
        buyer.setEmail(paymentRequest.getBuyerEmail());
        buyer.setIdentityNumber(paymentRequest.getBuyerIdentityNumber());
        buyer.setRegistrationAddress(paymentRequest.getBuyerAddress());
        buyer.setIp(paymentRequest.getIp());
        buyer.setCity(paymentRequest.getCity());
        buyer.setCountry(paymentRequest.getCountry());
        request.setBuyer(buyer);


        // Kart bilgilerini ayarla
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardHolderName(paymentRequest.getCardHolderName());
        paymentCard.setCardNumber(paymentRequest.getCardNumber());
        paymentCard.setExpireMonth(paymentRequest.getExpireMonth());
        paymentCard.setExpireYear(paymentRequest.getExpireYear());
        paymentCard.setCvc(paymentRequest.getCvc());
        request.setPaymentCard(paymentCard);
        request.setBuyer(buyer);


        // Adres bilgisi
        Address shippingAddress = new Address();
        shippingAddress.setContactName(paymentRequest.getBuyerName());
        shippingAddress.setCity(paymentRequest.getCity());
        shippingAddress.setCountry(paymentRequest.getCountry());
        shippingAddress.setAddress(paymentRequest.getBuyerAddress());
        shippingAddress.setZipCode(paymentRequest.getZipCode());
        request.setShippingAddress(shippingAddress);

// Billing Address
        Address billingAddress = new Address();
        billingAddress.setContactName(paymentRequest.getBuyerName());
        billingAddress.setCity(paymentRequest.getCity());
        billingAddress.setCountry(paymentRequest.getCountry());
        billingAddress.setAddress(paymentRequest.getBuyerAddress());
        billingAddress.setZipCode(paymentRequest.getZipCode());
        request.setBillingAddress(billingAddress);


        // Sepet bilgisi (zorunlu olabilir)
        List<PaymentItem> paymentItems = new ArrayList<>();
        PaymentItem paymentItem = new PaymentItem();
        paymentItem.setItemId("PI12345");
        paymentItem.setPrice(new BigDecimal("1.0"));
        paymentItems.add(paymentItem);

        request.setBasketId("B67832");



        // Ödeme işlemi
        com.iyzipay.model.Payment payment = com.iyzipay.model.Payment.create(request, IyzicoConfig.getOptions());

        payment.getErrorMessage();

        if (!payment.getStatus().equals("success")) {
            System.out.println("Ödeme başarısız!");
            System.out.println("Hata Kodu: " + payment.getErrorCode());
            System.out.println("Hata Mesajı: " + payment.getErrorMessage());

        }
        return payment;
    }

}

