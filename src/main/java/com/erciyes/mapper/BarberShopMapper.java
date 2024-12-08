package com.erciyes.mapper;

import com.erciyes.dto.DtoBarberShop;
import com.erciyes.dto.DtoHairdresser;
import com.erciyes.model.BarberShop;
import com.erciyes.model.Hairdresser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<DtoHairdresser> hairdresserDTOs = barberShop.getHairdresser().stream()
                .map(hairdresser -> new DtoHairdresser(hairdresser.getId(),hairdresser.getName(), hairdresser.getLastName(), hairdresser.getCurrencyType()))
                .collect(Collectors.toList());
        dtoBarberShop.setHairdressers(hairdresserDTOs);
        dtoBarberShop.setId(barberShop.getId());
        dtoBarberShop.setCreateTime(barberShop.getCreateTime());
        dtoBarberShop.setOpeningTime(barberShop.getOpeningTime());
        dtoBarberShop.setClosingTime(barberShop.getClosingTime());
        return dtoBarberShop;
    }

}
