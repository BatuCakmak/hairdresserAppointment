package com.erciyes.dto;

import com.erciyes.enums.AppointmentStatusType;
import com.erciyes.model.BarberShop;
import com.erciyes.model.Hairdresser;
import com.erciyes.model.Services;
import com.erciyes.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoAppointment extends DtoBase{
    private LocalTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private User user;
    private Services service;

    private Hairdresser hairdresser;

    private BarberShop barbershop;

}
