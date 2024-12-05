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

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoAppointment extends DtoBase{
    private LocalDateTime startTime;

    private LocalDateTime endTime;


    @ManyToOne
    private Services services;

    @ManyToOne
    @JoinColumn(name = "hairdresser_id")
    private Hairdresser hairdresser;

    @ManyToOne // Appointment ile Barbershop arasında ilişki
    private BarberShop barbershop;

}
