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
        dtoHairdresser.setId(hairdresser.getId());
        dtoHairdresser.setName(hairdresser.getName());
        dtoHairdresser.setPrice(hairdresser.getPrice());
        dtoHairdresser.setLastName(hairdresser.getLastName());

        List<DtoHairdresser> dtoHairdresserList=new ArrayList<>();
        return dtoHairdresser;
    }

    public static Hairdresser toEntity(DtoHairdresser dtoHairdresser) {
        if (dtoHairdresser==null){
            return null;
        }
        Hairdresser hairdresser=new Hairdresser();
        hairdresser.setId(dtoHairdresser.getId());
        hairdresser.setName(dtoHairdresser.getName());
        hairdresser.setPrice(dtoHairdresser.getPrice());
        hairdresser.setLastName(dtoHairdresser.getLastName());
        return hairdresser;
    }
}
