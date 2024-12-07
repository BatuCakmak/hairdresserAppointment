package com.erciyes.mapper;

import com.erciyes.dto.DtoBarberShop;
import com.erciyes.dto.DtoHairdresser;
import com.erciyes.model.BarberShop;
import com.erciyes.model.Hairdresser;
import org.springframework.stereotype.Component;

@Component
public class BarberShopMapper {

    public DtoBarberShop toDto(BarberShop barberShop) {
        if (barberShop == null) {
            return null;
        }
        DtoBarberShop dtoBarberShop = new DtoBarberShop();
        dtoBarberShop.setName(barberShop.getName());
        dtoBarberShop.setAddres(barberShop.getAddress());
        dtoBarberShop.setPhoneNumber(barberShop.getPhoneNumber());
//        dtoBarberShop.setHairdresser(barberShop.getHairdresser());
        return dtoBarberShop;
    }

}
