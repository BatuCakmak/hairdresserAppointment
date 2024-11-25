package com.erciyes.dto;

import com.erciyes.model.BarberShop;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoServices extends DtoBase{
    private String name;

    private int duration;

    private BigDecimal price;

    private BarberShop barberShop;
}
