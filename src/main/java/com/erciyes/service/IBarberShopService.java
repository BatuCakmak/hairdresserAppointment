package com.erciyes.service;

import com.erciyes.dto.DtoBarberShop;
import com.erciyes.model.BarberShop;


import java.util.List;

public interface IBarberShopService {
    public DtoBarberShop createBarberShop(BarberShop barberShop);
    public List<DtoBarberShop> getAllBarberShops();
    public DtoBarberShop getBarberShopById(Long id);
    public void deleteBarberShop(Long id);
    public void deleteBarberShopfromHairdresser(Long barberShopId);

    public DtoBarberShop updateBarberShop(Long id , BarberShop barberShop);
}
