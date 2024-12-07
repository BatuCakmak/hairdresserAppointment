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

    private int durationTime;

    @ManyToMany(mappedBy = "services")
    private List<Hairdresser> hairdressers;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

}
