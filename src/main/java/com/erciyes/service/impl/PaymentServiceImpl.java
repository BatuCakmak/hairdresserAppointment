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
    public com.iyzipay.model.Payment makePayment(CreatePaymentRequest createPaymentRequest) {
        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setLocale(Locale.TR.getValue());
        request.setConversationId("123456789");//orderId
        request.setPrice(createPaymentRequest.getPrice());
        request.setPaidPrice(createPaymentRequest.getPrice());
        request.setCurrency(Currency.TRY.name());
        request.setInstallment(1);

        // Kullanıcı bilgilerini ayarla
        Buyer buyer = new Buyer();
        buyer.setId("BY789");
        buyer.setName(createPaymentRequest.getBuyer().getName());
        buyer.setSurname(createPaymentRequest.getBuyer().getSurname());
        buyer.setEmail(createPaymentRequest.getBuyer().getEmail());
        buyer.setIdentityNumber(createPaymentRequest.getBuyer().getIdentityNumber());
        buyer.setRegistrationAddress(createPaymentRequest.getBuyer().getRegistrationAddress());
        buyer.setIp(createPaymentRequest.getBuyer().getIp());
        buyer.setCity(createPaymentRequest.getBuyer().getCity());
        buyer.setCountry(createPaymentRequest.getBuyer().getCountry());
        request.setBuyer(buyer);


        // Kart bilgilerini ayarla
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardHolderName(createPaymentRequest.getPaymentCard().getCardHolderName());
        paymentCard.setCardNumber(createPaymentRequest.getPaymentCard().getCardNumber());
        paymentCard.setExpireMonth(createPaymentRequest.getPaymentCard().getExpireMonth());
        paymentCard.setExpireYear(createPaymentRequest.getPaymentCard().getExpireYear());
        paymentCard.setCvc(createPaymentRequest.getPaymentCard().getCvc());
        paymentCard.setRegisterCard(0);
        request.setPaymentCard(paymentCard);
       // request.setBuyer(buyer);


        // Adres bilgisi
        Address shippingAddress = new Address();
        shippingAddress.setContactName(createPaymentRequest.getShippingAddress().getContactName());
        shippingAddress.setCity(createPaymentRequest.getShippingAddress().getCity());
        shippingAddress.setCountry(createPaymentRequest.getShippingAddress().getCountry());
        shippingAddress.setAddress(createPaymentRequest.getShippingAddress().getAddress());
        shippingAddress.setZipCode(createPaymentRequest.getShippingAddress().getZipCode());
        request.setShippingAddress(shippingAddress);

// Billing Address
        Address billingAddress = new Address();
        billingAddress.setContactName(createPaymentRequest.getBillingAddress().getContactName());
        billingAddress.setCity(createPaymentRequest.getBillingAddress().getCity());
        billingAddress.setCountry(createPaymentRequest.getBillingAddress().getCountry());
        billingAddress.setAddress(createPaymentRequest.getBillingAddress().getAddress());
        billingAddress.setZipCode(createPaymentRequest.getBillingAddress().getZipCode());
        request.setBillingAddress(billingAddress);


        // Sepet bilgisi (zorunlu olabilir)
        List<BasketItem> basketItems = new ArrayList<BasketItem>();
        BasketItem firstBasketItem = new BasketItem();
        firstBasketItem.setId("BI101");
        firstBasketItem.setName("Binocular");
        firstBasketItem.setCategory1("Collectibles");
        firstBasketItem.setCategory2("Accessories");
        firstBasketItem.setItemType(BasketItemType.PHYSICAL.name());
        firstBasketItem.setPrice(new BigDecimal("100.00"));
//        firstBasketItem.setSubMerchantKey("sub merchant key");
//        firstBasketItem.setSubMerchantPrice(new BigDecimal("100.00"));
        basketItems.add(firstBasketItem);
        request.setBasketItems(basketItems);



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

