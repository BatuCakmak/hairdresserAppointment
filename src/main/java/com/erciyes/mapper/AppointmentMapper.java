package com.erciyes.mapper;

import com.erciyes.dto.DtoAppointment;
import com.erciyes.model.Appointment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class AppointmentMapper {
    public DtoAppointment toDto(Appointment appointment) {
        if(appointment == null) return null;
        DtoAppointment dtoAppointment = new DtoAppointment();
        dtoAppointment.setHairdresserId(appointment.getHairdresser().getId());
        dtoAppointment.setServiceId(appointment.getService().getId());
        dtoAppointment.setBarbershopId(appointment.getBarbershop().getId());
        //dtoAppointment.setEndTime(appointment.getEndTime());
        dtoAppointment.setStartTime(LocalTime.from(LocalDateTime.from(appointment.getStartTime())));
        dtoAppointment.setId(appointment.getId());
        dtoAppointment.setCreateTime(appointment.getCreateTime());
        return dtoAppointment;
    }
}
