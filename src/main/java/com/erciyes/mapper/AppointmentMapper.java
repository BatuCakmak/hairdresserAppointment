package com.erciyes.mapper;

import com.erciyes.dto.DtoAppointment;
import com.erciyes.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public DtoAppointment toDto(Appointment appointment) {
        if(appointment == null) return null;
        DtoAppointment dtoAppointment = new DtoAppointment();
        dtoAppointment.setAppointmentDateTime(appointment.getAppointmentDateTime());
        dtoAppointment.setHairdresser(appointment.getHairdresser());
        dtoAppointment.setAppointmentStatusType(appointment.getAppointmentStatusType());
        dtoAppointment.setUser(appointment.getUser());
        return dtoAppointment;
    }
}
