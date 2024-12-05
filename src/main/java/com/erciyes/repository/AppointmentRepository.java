package com.erciyes.repository;

import com.erciyes.model.Appointment;
import com.erciyes.model.BarberShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByBarbershopAndStartTimeBetween(BarberShop barberShop, LocalDateTime start, LocalDateTime end);
}
