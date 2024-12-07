package com.erciyes.model;

import com.erciyes.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hair_dresser")
public class Hairdresser extends BaseEntity{

    private String name;

    private String lastName;

//    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @ManyToOne
    //@JoinColumn(name = "barber_shop_id")
    private BarberShop barberShop;

    @OneToMany(mappedBy = "hairdresser",cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @ManyToMany
    private List<Services> services;

}
