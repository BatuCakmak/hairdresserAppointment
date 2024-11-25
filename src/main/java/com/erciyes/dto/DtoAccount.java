package com.erciyes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoAccount extends DtoBase{
    private String accountNo;

    private BigDecimal amount;
}
