package com.erciyes.mapper;

import com.erciyes.dto.DtoHairdresser;
import com.erciyes.model.Hairdresser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HairdresserMapper {
    public DtoHairdresser toDto(Hairdresser hairdresser) {
        if (hairdresser == null) {
            return null;
        }
        DtoHairdresser dtoHairdresser = new DtoHairdresser();
        dtoHairdresser.setName(hairdresser.getName());
        dtoHairdresser.setPrice(hairdresser.getPrice());
        dtoHairdresser.setLastName(hairdresser.getLastName());
        return dtoHairdresser;

    }
    
}
