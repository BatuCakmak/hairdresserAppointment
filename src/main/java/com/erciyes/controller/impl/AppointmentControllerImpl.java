package com.erciyes.controller.impl;

import com.erciyes.controller.IAppointmentController;
import com.erciyes.dto.DtoAppointment;
import com.erciyes.model.Appointment;
import com.erciyes.service.IAppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentControllerImpl implements IAppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @PostMapping("/create")
    @Override
    public DtoAppointment createAppointment(@RequestBody @Valid Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @GetMapping("/list")
    @Override
    public List<DtoAppointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/list/{id}")
    @Override
    public DtoAppointment getAppointmentById(@PathVariable(name= "id") Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @DeleteMapping("/delete")
    @Override
    public void deleteAppointment(@PathVariable(name= "id") Long id) {
        appointmentService.deleteAppointment(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public DtoAppointment updateAppointment(@PathVariable(name= "id") Long id, @RequestBody @Valid Appointment appointment) {
        return appointmentService.updateAppointment(id, appointment);
    }
}
