package com.erciyes.model;

import com.erciyes.enums.CurrencyType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity{

    private String accountNo;

    private String iban;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
}
