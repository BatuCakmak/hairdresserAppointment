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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hair_dresser")
public class Hairdresser extends BaseEntity{

    private String name;

    private String lastName;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;


}
