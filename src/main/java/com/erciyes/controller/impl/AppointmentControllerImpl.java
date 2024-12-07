package com.erciyes.controller.impl;

import com.erciyes.controller.IAppointmentController;
import com.erciyes.dto.DtoAppointment;
import com.erciyes.enums.ServiceType;
import com.erciyes.model.Appointment;
import com.erciyes.model.BarberShop;
import com.erciyes.repository.BarberShopRepository;
import com.erciyes.service.IAppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentControllerImpl implements IAppointmentController {


    private  IAppointmentService appointmentService;
    private  BarberShopRepository barbershopRepository;

    @Autowired
    public void AppointmentController(IAppointmentService appointmentService, BarberShopRepository barbershopRepository) {
        this.appointmentService = appointmentService;
        this.barbershopRepository = barbershopRepository;
    }


    @GetMapping("/available-slots")
    @Override
    public List<LocalDateTime> getAvailableTimeSlots(
            @RequestParam Long barbershopId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam ServiceType serviceType) {
        BarberShop barbershop = barbershopRepository.findById(barbershopId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Barbershop not found"));
        return appointmentService.getAvailableTimeSlots(barbershop, date, serviceType);
    }

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
