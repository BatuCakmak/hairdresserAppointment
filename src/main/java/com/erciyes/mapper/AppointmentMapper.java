package com.erciyes.mapper;

import com.erciyes.dto.DtoAppointment;
import com.erciyes.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public DtoAppointment toDto(Appointment appointment) {
        if(appointment == null) return null;
        DtoAppointment dtoAppointment = new DtoAppointment();
        dtoAppointment.setHairdresser(appointment.getHairdresser());
        dtoAppointment.setServices(appointment.getServices());
        dtoAppointment.setEndTime(appointment.getEndTime());
        dtoAppointment.setStartTime(appointment.getStartTime());
        dtoAppointment.setCreateTime(appointment.getCreateTime());
        dtoAppointment.setId(appointment.getId());
        dtoAppointment.setCreateTime(appointment.getCreateTime());
        return dtoAppointment;
    }
}
