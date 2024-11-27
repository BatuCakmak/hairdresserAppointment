package com.erciyes.controller;

import com.erciyes.dto.DtoAppointment;
import com.erciyes.model.Appointment;

import java.util.List;

public interface IAppointmentController {

    public DtoAppointment createAppointment(Appointment appointment);
    public List<DtoAppointment> getAllAppointments();
    public DtoAppointment getAppointmentById(Long id);
    public void deleteAppointment(Long id);
    public DtoAppointment updateAppointment(Long id , Appointment appointment);
}
