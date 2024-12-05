package com.erciyes.controller;

import com.erciyes.dto.DtoAppointment;
import com.erciyes.enums.ServiceType;
import com.erciyes.model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentController {

    public DtoAppointment createAppointment(Appointment appointment);
    public List<DtoAppointment> getAllAppointments();
    public DtoAppointment getAppointmentById(Long id);
    public void deleteAppointment(Long id);
    public DtoAppointment updateAppointment(Long id , Appointment appointment);
    public List<LocalDateTime> getAvailableTimeSlots(Long barberId, LocalDate date, ServiceType serviceType);
}
