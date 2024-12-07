package com.erciyes.model;

import com.erciyes.enums.AppointmentStatusType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    private LocalTime startTime;

    private LocalTime endTime;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private AppointmentStatusType statusType;

    @ManyToOne
    @JoinColumn(name = "hairdresser_id")
    private Hairdresser hairdresser;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services service;

    @ManyToOne
    @JoinColumn(name = "barbershop_id")
    private BarberShop barbershop;

    @OneToOne
    private User user;


}
