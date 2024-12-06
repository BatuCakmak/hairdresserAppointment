package com.erciyes.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
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

    @OneToMany
    private List<Hairdresser> hairdressers;





}
