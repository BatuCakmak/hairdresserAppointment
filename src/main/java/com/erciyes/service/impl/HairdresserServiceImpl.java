package com.erciyes.service.impl;

import com.erciyes.dto.DtoHairdresser;
import com.erciyes.mapper.HairdresserMapper;
import com.erciyes.model.Hairdresser;
import com.erciyes.repository.HairdresserRepository;
import com.erciyes.service.IHairdresserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HairdresserServiceImpl implements IHairdresserService {

    @Autowired
    private HairdresserRepository hairdresserRepository;

    @Autowired
    private HairdresserMapper hairdresserMapper;

    @Override
    public DtoHairdresser createHairdresser(DtoHairdresser dtoHairdresser) {
        Hairdresser hairdresser =HairdresserMapper.toEntity(dtoHairdresser);
        Hairdresser dbHairdresser=hairdresserRepository.save(hairdresser);
        return hairdresserMapper.toDto(dbHairdresser);
    }

    @Override
    public List<DtoHairdresser> getAllHairdressers() {
        List<Hairdresser> hairdresserList =hairdresserRepository.findAll();
        List<DtoHairdresser> dtoHairdresserList=new ArrayList<>();
        for (Hairdresser hairdresser:hairdresserList){
            DtoHairdresser dtoHairdresser=hairdresserMapper.toDto(hairdresser);
            dtoHairdresserList.add(dtoHairdresser);
        }
        return dtoHairdresserList;
    }

    @Override
    public DtoHairdresser getHairdresserById(Long id) {
        Optional<Hairdresser> optional=hairdresserRepository.findById(id);
        if (optional.isPresent()){
            DtoHairdresser dtoHairdresser=hairdresserMapper.toDto(optional.get());
            return dtoHairdresser;
        }
        return null;
    }

    @Override
    public void deleteHairdresser(Long id) {
        hairdresserRepository.deleteById(id);
    }

    @Override
    public DtoHairdresser updateHairdresser(Long id, DtoHairdresser dtoHairdresser) {
        Optional<Hairdresser> optional=hairdresserRepository.findById(id);
        if (optional.isPresent()){
            Hairdresser hairdresser=hairdresserMapper.toEntity(dtoHairdresser);
            hairdresser.setId(id);
            Hairdresser dbHairdresser=hairdresserRepository.save(hairdresser);
            return hairdresserMapper.toDto(dbHairdresser);
        }
        return null;
    }
}
