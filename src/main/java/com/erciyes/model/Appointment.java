package com.erciyes.model;

import com.erciyes.enums.AppointmentStatusType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointment")
public class Appointment extends BaseEntity{

    private LocalDateTime appointmentDateTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatusType appointmentStatusType;

    @ManyToOne
    private User user;

    @ManyToOne
    private Hairdresser hairdresser;


}
