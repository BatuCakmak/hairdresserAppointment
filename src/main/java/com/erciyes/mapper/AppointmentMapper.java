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
        dtoAppointment.setHairdresser(appointment.getHairdresser());
        dtoAppointment.setService(appointment.getService());
        dtoAppointment.setBarbershop(appointment.getBarbershop());
        dtoAppointment.setEndTime(appointment.getEndTime().toLocalTime());
        dtoAppointment.setStartTime(LocalTime.from(LocalDateTime.from(appointment.getStartTime())));
        dtoAppointment.setId(appointment.getId());
//        dtoAppointment.setUser(appointment.getUser());
        dtoAppointment.setCreateTime(appointment.getCreateTime());
        dtoAppointment.setDate(appointment.getDate().toLocalDate());
        return dtoAppointment;
    }
}
