package com.erciyes.controller;

import com.erciyes.dto.DtoAppointment;
import com.erciyes.dto.DtoBarberShop;
import com.erciyes.enums.ServiceType;
import com.erciyes.model.Appointment;
import com.erciyes.model.BarberShop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IBarberShopController {
    public DtoBarberShop createBarberShop(BarberShop barberShop);
    public List<DtoBarberShop> getAllBarberShops();
    public DtoBarberShop getBarberShopById(Long id);
    public void deleteBarberShop(Long id);
    public DtoBarberShop updateBarberShop(Long id , BarberShop barberShop);
}
