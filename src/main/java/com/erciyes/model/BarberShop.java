package com.erciyes.model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "barber_shop")
public class BarberShop extends BaseEntity{

    private String name;

    private String phoneNumber;

    private String description;

    private LocalTime openingTime;

    private LocalTime closingTime;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "barbershop")
    private List<Hairdresser> hairdresser;





}
