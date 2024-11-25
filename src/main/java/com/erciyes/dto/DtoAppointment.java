package com.erciyes.dto;

import com.erciyes.enums.AppointmentStatusType;
import com.erciyes.model.Hairdresser;
import com.erciyes.model.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoAppointment extends DtoBase{
    private AppointmentStatusType appointmentStatusType;
    private LocalDateTime appointmentDateTime;
    private User user;
    private Hairdresser hairdresser;

}
