package com.erciyes.dto;

import com.erciyes.enums.AppointmentStatusType;
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
}
