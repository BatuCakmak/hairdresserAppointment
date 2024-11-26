package com.erciyes.service;

import com.erciyes.dto.DtoHairdresser;
import com.erciyes.model.Hairdresser;

import java.util.List;

public interface IHairdresserService{
    public DtoHairdresser createHairdresser(Hairdresser hairdresser);
    public List<DtoHairdresser> getAllHairdressers();
    public DtoHairdresser getHairdresserById(Long id);
    public void deleteHairdresser(Long id);
    public DtoHairdresser updateHairdresser(Long id , Hairdresser hairdresser);
}
