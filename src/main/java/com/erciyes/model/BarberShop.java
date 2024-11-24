package com.erciyes.model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "barber_shop")
public class BarberShop extends BaseEntity{

    private String name;

    private String phoneNumber;

    @OneToOne
    private Addres addres;

    @OneToOne
    private Hairdresser hairdresser;


}
