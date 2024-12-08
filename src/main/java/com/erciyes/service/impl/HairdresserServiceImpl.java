package com.erciyes.service.impl;

import com.erciyes.dto.DtoHairdresser;
import com.erciyes.exception.BaseException;
import com.erciyes.exception.ErrorMessage;
import com.erciyes.exception.MessageType;
import com.erciyes.mapper.HairdresserMapper;
import com.erciyes.model.Hairdresser;
import com.erciyes.repository.BarberShopRepository;
import com.erciyes.repository.HairdresserRepository;
import com.erciyes.service.IHairdresserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HairdresserServiceImpl implements IHairdresserService {

    @Autowired
    private HairdresserRepository hairdresserRepository;

    @Autowired
    private HairdresserMapper hairdresserMapper;

    @Override
    public DtoHairdresser createHairdresser(Hairdresser hairdresser) {
        hairdresser.setCreateTime(new Date());
        DtoHairdresser dtoHairdresser = new DtoHairdresser();
        if(hairdresserRepository.existsHairdresserByNameAndLastName(hairdresser.getName(), hairdresser.getLastName())) {
            throw new BaseException(new ErrorMessage(MessageType.HAIRDRESSER_ALREADY_EXIST,hairdresser.getName()+ hairdresser.getLastName()));
        }else {
            hairdresserRepository.save(hairdresser);
        }
        BeanUtils.copyProperties(hairdresser, dtoHairdresser);
        return dtoHairdresser;
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
    public DtoHairdresser updateHairdresser(Long id, Hairdresser hairdresser) {
        Optional<Hairdresser> optional=hairdresserRepository.findById(id);
        if (optional.isPresent()){
            hairdresser.setId(id);
            Hairdresser dbHairdresser=hairdresserRepository.save(hairdresser);
            return hairdresserMapper.toDto(dbHairdresser);
        }
        return null;
    }
}
