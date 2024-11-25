package com.erciyes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoHairdresser extends DtoBase{
    private String name;

    private String lastName;
    private BigDecimal price;
}
