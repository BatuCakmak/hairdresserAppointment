package com.erciyes.service.impl;

import com.erciyes.dto.DtoAppointment;
import com.erciyes.enums.ServiceType;
import com.erciyes.mapper.AppointmentMapper;
import com.erciyes.model.Appointment;
import com.erciyes.model.BarberShop;
import com.erciyes.repository.AppointmentRepository;
import com.erciyes.service.IAppointmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;
    @Override
    public DtoAppointment createAppointment(Appointment appointment) {
        appointment.setCreateTime(new Date());
        DtoAppointment dtoAppointment = new DtoAppointment();
        appointmentRepository.save(appointment);
        BeanUtils.copyProperties(appointment, dtoAppointment);
        return dtoAppointment;
    }

    @Override
    public List<DtoAppointment> getAllAppointments() {
        List<Appointment> appointmentList =appointmentRepository.findAll();
        List<DtoAppointment> dtoAppointmentList=new ArrayList<>();
        for (Appointment appointment:appointmentList){
            DtoAppointment dtoAppointment =appointmentMapper.toDto(appointment);
            dtoAppointmentList.add(dtoAppointment);
        }
        return dtoAppointmentList;
    }

    @Override
    public DtoAppointment getAppointmentById(Long id) {
        Optional<Appointment> optional=appointmentRepository.findById(id);
        if (optional.isPresent()){
            DtoAppointment dtoAppointment=appointmentMapper.toDto(optional.get());
            return dtoAppointment;
        }
        return null;
    }

    @Override
    public void deleteAppointment(Long id) {
         appointmentRepository.deleteById(id);
    }

    @Override
    public DtoAppointment updateAppointment(Long id, Appointment appointment) {
        Optional<Appointment> optional=appointmentRepository.findById(id);
        if (optional.isPresent()){
            appointment.setId(id);
            Appointment dbAppointment=appointmentRepository.save(appointment);
            return appointmentMapper.toDto(dbAppointment);
        }
        return null;
    }

    @Override
    public List<LocalDateTime> getAvailableTimeSlots(BarberShop barbershop, LocalDate date, ServiceType serviceType) {
        LocalTime openingTime = barbershop.getOpeningTime();
        LocalTime closingTime = barbershop.getClosingTime();

        List<LocalDateTime> availableSlots = new ArrayList<>();
        LocalDateTime currentSlot = date.atTime(openingTime);

        while (!currentSlot.isAfter(date.atTime(closingTime.minusMinutes(serviceType.getDuration())))) {
            availableSlots.add(currentSlot);
            currentSlot = currentSlot.plusMinutes(30);
        }

        // Zaten dolu olan slotları filtrele
        List<Appointment> existingAppointments = appointmentRepository.findByBarbershopAndStartTimeBetween(
                barbershop, date.atTime(openingTime), date.atTime(closingTime)
        );

        return availableSlots.stream()
                .filter(slot -> existingAppointments.stream()
                        .noneMatch(appointment ->
                                !slot.plusMinutes(serviceType.getDuration()).isBefore(appointment.getStartTime()) &&
                                        !slot.isAfter(appointment.getEndTime())
                        )
                )
                .collect(Collectors.toList());
    }
}
