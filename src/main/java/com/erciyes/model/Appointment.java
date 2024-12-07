package com.erciyes.model;

import com.erciyes.enums.AppointmentStatusType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointment")
public class Appointment extends BaseEntity{

//    private LocalDateTime appointmentDateTime;

//    @Enumerated(EnumType.STRING)
//    private AppointmentStatusType appointmentStatusType;

//    @ManyToOne
//    private User user;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "hairdresser_id")
    private Hairdresser hairdresser;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services service;

    @ManyToOne
    @JoinColumn(name = "barbershop_id")
    private BarberShop barbershop;



}
