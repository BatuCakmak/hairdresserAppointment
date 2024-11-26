package com.erciyes.service.impl;

import com.erciyes.dto.DtoBarberShop;
import com.erciyes.dto.DtoHairdresser;
import com.erciyes.exception.BaseException;
import com.erciyes.exception.ErrorMessage;
import com.erciyes.exception.MessageType;
import com.erciyes.mapper.BarberShopMapper;
import com.erciyes.mapper.HairdresserMapper;
import com.erciyes.model.BarberShop;
import com.erciyes.model.Hairdresser;
import com.erciyes.repository.BarberShopRepository;
import com.erciyes.repository.HairdresserRepository;
import com.erciyes.service.IBarberShopService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BarberShopServiceImpl implements IBarberShopService {

    @Autowired
    private BarberShopRepository barberShopRepository;

    @Autowired
    private BarberShopMapper barberShopMapper;

    @Override
    public DtoBarberShop createBarberShop(BarberShop barberShop) {
        barberShop.setCreateTime(new Date());
        DtoBarberShop dtoBarberShop = new DtoBarberShop();
        barberShopRepository.save(barberShop);
        BeanUtils.copyProperties(barberShop, dtoBarberShop);
        return dtoBarberShop;
    }

    @Override
    public List<DtoBarberShop> getAllBarberShops() {
        List<BarberShop> barberShopList =barberShopRepository.findAll();
        List<DtoBarberShop> dtoBarberShopList=new ArrayList<>();
        for (BarberShop barberShop:barberShopList){
            DtoBarberShop dtoBarberShop =barberShopMapper.toDto(barberShop);
            dtoBarberShopList.add(dtoBarberShop);
        }
        return dtoBarberShopList;
    }

    @Override
    public DtoBarberShop getBarberShopById(Long id) {
        Optional<BarberShop> optional=barberShopRepository.findById(id);
        if (optional.isPresent()){
            DtoBarberShop dtoBarberShop=barberShopMapper.toDto(optional.get());
            return dtoBarberShop;
        }
        return null;
    }

    @Override
    public void deleteBarberShop(Long id) {
          barberShopRepository.deleteById(id);
    }

    @Override
    public DtoBarberShop updateBarberShop(Long id, BarberShop barberShop) {
        Optional<BarberShop> optional=barberShopRepository.findById(id);
        if (optional.isPresent()){
            barberShop.setId(id);
            BarberShop dbBarberShop=barberShopRepository.save(barberShop);
            return barberShopMapper.toDto(dbBarberShop);
        }
        return null;
    }
}
