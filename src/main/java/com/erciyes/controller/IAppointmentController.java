package com.erciyes.controller;

import com.erciyes.dto.DtoAppointment;
import com.erciyes.dto.TimeSlot;
import com.erciyes.enums.ServiceType;
import com.erciyes.model.Appointment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentController {

    public DtoAppointment createAppointment(DtoAppointment appointment);

    public List<DtoAppointment> getAllAppointments();

    public DtoAppointment getAppointmentById(Long id);

    public void deleteAppointment(Long id);

    public DtoAppointment updateAppointment(Long id, Appointment appointment);

    public ResponseEntity<List<TimeSlot>> getAvailableTimeSlots(Long barbershopId, LocalDate day);

}
