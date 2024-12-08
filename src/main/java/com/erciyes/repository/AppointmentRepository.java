package com.erciyes.repository;

import com.erciyes.model.Appointment;
import com.erciyes.model.BarberShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByBarbershopAndStartTimeBetween(BarberShop barberShop, LocalDateTime start, LocalDateTime end);

    @Query("SELECT a FROM Appointment a WHERE a.barbershop.id = :barbershopId " +
            "AND ((:startTime BETWEEN a.startTime AND a.endTime) OR " +
            "(:endTime BETWEEN a.startTime AND a.endTime) OR " +
            "(a.startTime BETWEEN :startTime AND :endTime))")
    List<Appointment> findOverlappingAppointmentsForBarberShop(
            @Param("barbershopId") Long barbershopId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

}
