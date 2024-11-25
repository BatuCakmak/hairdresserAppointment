package com.erciyes.service;

import com.erciyes.dto.DtoHairdresser;

import java.util.List;

public interface IHairdresserService{
    public DtoHairdresser createHairdresser(DtoHairdresser dtoHairdresser);
    public List<DtoHairdresser> getAllHairdressers();
    public DtoHairdresser getHairdresserById(Long id);
    public void deleteHairdresser(Long id);
    public DtoHairdresser updateHairdresser(Long id , DtoHairdresser dtoHairdresser);
}
