package com.erciyes.service.impl;

import com.erciyes.dto.DtoAppointment;
import com.erciyes.dto.TimeSlot;
import com.erciyes.enums.AppointmentStatusType;
import com.erciyes.enums.ServiceType;
import com.erciyes.exception.MessageType;
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
import java.util.*;
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

            BarberShop barberShop = barberShopRepository.findById(request.getBarbershop().getId())
                    .orElseThrow(() -> new RuntimeException("Barber shop not found"));

            Services service = servicesRepository.findById(request.getService().getId())
                    .orElseThrow(() -> new RuntimeException("Service not found"));

            User user = userRepository.findById(request.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

//            LocalDateTime startDateTime = LocalDateTime.of(request.getDate(), request.getStartTime());
//            LocalTime endDateTime = LocalTime.from(startDateTime.plusMinutes(service.getDuration()));

        LocalDateTime startTime = LocalDateTime.of(request.getDate(), request.getStartTime());
        LocalDateTime endTime = startTime.plusMinutes(service.getDuration());


        if (!isSlotAvailable(barberShop.getId(), request.getDate(), request.getStartTime(), request.getStartTime().plusMinutes(service.getDuration()))) {
                throw new RuntimeException("Seçilen zaman dilimi uygun değil!");
            }

            Appointment appointment = new Appointment();
            appointment.setCreateTime(new Date());
            appointment.setBarbershop(request.getBarbershop());
            appointment.setHairdresser(request.getHairdresser());
            appointment.setService(request.getService());
            appointment.setUser(request.getUser());
            appointment.setStartTime(startTime);
            appointment.setEndTime(endTime);
            appointment.setDate(request.getDate().atStartOfDay());
            appointment.setStatusType(AppointmentStatusType.BOOKED);


            appointmentRepository.save(appointment);
        return  appointmentMapper.toDto(appointment);
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
        Optional<Appointment> optAppointment=appointmentRepository.findById(id);
        if (optAppointment.isPresent()){
            appointmentRepository.deleteById(optAppointment.get().getId());
        }

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

    @Override
    public Map<LocalDate, List<TimeSlot>> getWeeklyAvailableTimeSlots(Long barbershopId, LocalDate startDate) {
        // LinkedHashMap kullanarak gün sırasının (Pzt, Sal, Çrş...) korunmasını sağlıyoruz.
        Map<LocalDate, List<TimeSlot>> weeklyAvailability = new LinkedHashMap<>();

        // 'startDate' (Pazartesi) tarihinden başlayarak 7 gün boyunca döner
        for (int i = 0; i < 7; i++) {
            LocalDate currentDay = startDate.plusDays(i);

            // Zaten var olan günlük metodunuzu çağırıyoruz
            List<TimeSlot> dailySlots = this.getAvailableTimeSlots(barbershopId, currentDay);

            // O günün tarihini (key) ve o günün slot listesini (value) Map'e ekliyoruz
            weeklyAvailability.put(currentDay, dailySlots);
        }

        return weeklyAvailability; // 7 günlük veriyi tek seferde döndür
    }
    // Zaman diliminin müsaitliğini kontrol et


    private boolean isSlotAvailable(Long barbershopId, LocalDate day, LocalTime startTime, LocalTime endTime) {
        LocalDateTime startDateTime = LocalDateTime.of(day, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(day, endTime);

        return appointmentRepository.findOverlappingAppointmentsForBarberShop(barbershopId, startDateTime, endDateTime).isEmpty();
    }


    //debug control
    /*
    private boolean isSlotAvailable(Long barbershopId, LocalDate day, LocalTime startTime, LocalTime endTime) {
        LocalDateTime startDateTime = LocalDateTime.of(day, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(day, endTime);

        // ---- DEBUG BAŞLANGIÇ ----
        // Hangi slotu kontrol ettiğimizi görelim
        System.out.println("--- KONTROL EDİLİYOR ---");
        System.out.println("BarberShop ID: " + barbershopId);
        System.out.println("Slot Başlangıç: " + startDateTime);
        System.out.println("Slot Bitiş: " + endDateTime);

        // Sorguyu çalıştır
        List<Appointment> overlaps = appointmentRepository.findOverlappingAppointmentsForBarberShop(barbershopId, startDateTime, endDateTime);

        // Sorgu bir şey buldu mu?
        System.out.println("Çakışan Randevu Sayısı: " + overlaps.size());

        // Eğer çakışma bulduysa, o randevuları yazdıralım
        if (!overlaps.isEmpty()) {
            System.out.println("BULUNAN ÇAKIŞMA(LAR):");
            overlaps.forEach(app -> System.out.println(
                    " - ID: " + app.getId() +
                            " | DB Başlangıç: " + app.getStartTime() +
                            " | DB Bitiş: " + app.getEndTime()
            ));
        }

        boolean isAvailable = overlaps.isEmpty();
        System.out.println("Sonuç: Müsait mi?: " + isAvailable);
        System.out.println("-------------------------");
        // ---- DEBUG BİTİŞ ----

        return isAvailable;
    }
    */



}
