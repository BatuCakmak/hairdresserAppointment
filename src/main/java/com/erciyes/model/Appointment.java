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
    @ManyToOne // Appointment ile Barbershop arasında ilişki
    private BarberShop barbershop;

    @ManyToOne
    private Services services;

    @ManyToOne
    @JoinColumn(name = "hairdresser_id")
    private Hairdresser hairdresser;



}
