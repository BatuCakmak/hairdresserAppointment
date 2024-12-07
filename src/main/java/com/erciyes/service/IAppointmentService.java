package com.erciyes.service;


import com.erciyes.dto.DtoAppointment;
import com.erciyes.dto.TimeSlot;
import com.erciyes.enums.ServiceType;
import com.erciyes.model.Appointment;
import com.erciyes.model.BarberShop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {
    public DtoAppointment createAppointment(DtoAppointment appointment);
    public List<DtoAppointment> getAllAppointments();
    public DtoAppointment getAppointmentById(Long id);
    public void deleteAppointment(Long id);
    public DtoAppointment updateAppointment(Long id , Appointment appointment);
    public List<TimeSlot> getAvailableTimeSlots(Long barbershopId, LocalDate day);
}
