package com.erciyes.model;

import com.erciyes.enums.AppointmentStatusType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
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
