package com.erciyes.service.impl;

import com.erciyes.dto.DtoBarberShop;
import com.erciyes.mapper.BarberShopMapper;
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

    @Autowired
    private HairdresserRepository hairdresserRepository;

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
    public void deleteBarberShopfromHairdresser(Long barberShopId) {
        Optional<BarberShop> barberShop=barberShopRepository.findById(barberShopId);
        BarberShop current=barberShop.get();
        current.getHairdresser().clear();
        updateBarberShop(current.getId(),current);
    }

    @Override
    public void deleteBarberShop(Long id) {
        Optional<BarberShop> optional=barberShopRepository.findById(id);

        //deleteBarberShopfromHairdresser(id);
        if (optional.isPresent()){
            barberShopRepository.delete(optional.get());
        }
    }

    @Override
    public DtoBarberShop updateBarberShop(Long id, BarberShop barberShop) {
        Optional<BarberShop> optional=barberShopRepository.findById(id);
        if (optional.isPresent()){
            BarberShop dbBarberShop=optional.get();


            dbBarberShop.setName(barberShop.getName());
            dbBarberShop.setPhoneNumber(barberShop.getPhoneNumber());
            dbBarberShop.setDescription(barberShop.getDescription());
            dbBarberShop.setOpeningTime(barberShop.getOpeningTime());
            dbBarberShop.setClosingTime(barberShop.getClosingTime());
            dbBarberShop.setAddress(barberShop.getAddress());



            List<Hairdresser> hairdresserList=new ArrayList<>();
            for (Hairdresser hairdresser : barberShop.getHairdresser()){
                hairdresserList.add(hairdresser);
            }
            dbBarberShop.setHairdresser(hairdresserList);


            BarberShop updatedBarberShop=barberShopRepository.save(dbBarberShop);
            return barberShopMapper.toDto(updatedBarberShop);

        }
        return null;
    }
}
