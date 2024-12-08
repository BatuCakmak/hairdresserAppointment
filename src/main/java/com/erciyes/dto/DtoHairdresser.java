package com.erciyes.dto;

import com.erciyes.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoHairdresser extends DtoBase{
    private Long id;
    private String name;

    private String lastName;

    private CurrencyType currencyType;

}
