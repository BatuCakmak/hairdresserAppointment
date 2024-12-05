package com.erciyes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Services extends BaseEntity{

    private String name;

    private int duration;

    private BigDecimal price;

    @ManyToOne
    private BarberShop barberShop;
//    @ManyToOne(optional = false)
//    private Appointment appointments;

//    public Appointment getAppointments() {
//        return appointments;
//    }
//
//    public void setAppointments(Appointment appointments) {
//        this.appointments = appointments;
//    }
}
