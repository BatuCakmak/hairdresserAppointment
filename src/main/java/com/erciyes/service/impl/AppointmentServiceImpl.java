package com.erciyes.service.impl;

import com.erciyes.dto.DtoAppointment;
import com.erciyes.dto.TimeSlot;
import com.erciyes.enums.AppointmentStatusType;
import com.erciyes.enums.ServiceType;
import com.erciyes.mapper.AppointmentMapper;
import com.erciyes.model.Appointment;
import com.erciyes.model.BarberShop;
import com.erciyes.model.Services;
import com.erciyes.model.User;
import com.erciyes.repository.AppointmentRepository;
import com.erciyes.repository.BarberShopRepository;
import com.erciyes.repository.ServicesRepository;
import com.erciyes.repository.UserRepository;
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

    @Autowired
    private BarberShopRepository barberShopRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DtoAppointment createAppointment(DtoAppointment request) {
//        appointment.setCreateTime(new Date());
//        DtoAppointment dtoAppointment = new DtoAppointment();
//        appointmentRepository.save(appointment);
//        BeanUtils.copyProperties(appointment, dtoAppointment);
//        return dtoAppointment;

            BarberShop barberShop = barberShopRepository.findById(request.getBarbershopId())
                    .orElseThrow(() -> new RuntimeException("Barber shop not found"));

            Services service = servicesRepository.findById(request.getServiceId())
                    .orElseThrow(() -> new RuntimeException("Service not found"));

            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            LocalDateTime startDateTime = LocalDateTime.of(request.getDate(), request.getStartTime());
            LocalTime endDateTime = LocalTime.from(startDateTime.plusMinutes(service.getDuration()));

            if (!isSlotAvailable(barberShop.getId(), request.getDate(), request.getStartTime(), request.getStartTime().plusMinutes(service.getDuration()))) {
                throw new RuntimeException("Seçilen zaman dilimi uygun değil!");
            }

            Appointment appointment = new Appointment();
            appointment.setCreateTime(new Date());
            appointment.setBarbershop(barberShop);
            appointment.setService(service);
            appointment.setUser(user);
            appointment.setStartTime(LocalTime.from(startDateTime));
            appointment.setEndTime(LocalTime.from(endDateTime));
            appointment.setStatusType(AppointmentStatusType.BOOKED);

            appointmentRepository.save(appointment);
        return null;
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

    public List<TimeSlot> getAvailableTimeSlots(Long barbershopId, LocalDate day) {
        BarberShop barberShop = barberShopRepository.findById(barbershopId)
                .orElseThrow(() -> new RuntimeException("Barber shop not found"));

        LocalTime openingTime = barberShop.getOpeningTime();
        LocalTime closingTime = barberShop.getClosingTime();

        List<TimeSlot> timeSlots = new ArrayList<>();
        LocalTime currentTime = openingTime;

        while (currentTime.isBefore(closingTime)) {
            LocalTime endTime = currentTime.plusMinutes(30);
            boolean isAvailable = isSlotAvailable(barbershopId, day, currentTime, endTime);
            timeSlots.add(new TimeSlot(currentTime, endTime, isAvailable));
            currentTime = endTime;
        }

        return timeSlots;
    }

    // Zaman diliminin müsaitliğini kontrol et
    private boolean isSlotAvailable(Long barbershopId, LocalDate day, LocalTime startTime, LocalTime endTime) {
        LocalDateTime startDateTime = LocalDateTime.of(day, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(day, endTime);

        return appointmentRepository.findOverlappingAppointmentsForBarberShop(barbershopId, startDateTime, endDateTime).isEmpty();
    }

}
