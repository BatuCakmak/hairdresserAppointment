package com.erciyes.dto;

import com.erciyes.model.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest extends BaseEntity {
    private BigDecimal price;
    private String cardHolderName;
    private String cardNumber;
    private String expireMonth;
    private String expireYear;
    private String cvc;
    private String buyerName;
    private String buyerSurname;
    private String buyerEmail;
    private String buyerIdentityNumber;
    private String buyerAddress;
    private String city;
    private String country;
    private String ip;
    private String zipCode;
}
