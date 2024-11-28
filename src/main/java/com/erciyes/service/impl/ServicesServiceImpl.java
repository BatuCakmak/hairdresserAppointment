package com.erciyes.service.impl;

import com.erciyes.dto.DtoServices;
import com.erciyes.mapper.ServiceMapper;
import com.erciyes.model.Address;
import com.erciyes.model.Services;
import com.erciyes.repository.ServicesRepository;
import com.erciyes.service.IServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesServiceImpl implements IServicesService {

    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    private ServiceMapper serviceMapper;

    @Override
    public DtoServices createService(Services services) {
        services.setCreateTime(new Date());
        servicesRepository.save(services);
        DtoServices dtoServices = serviceMapper.toDto(services);
        return dtoServices;
    }

    @Override
    public List<DtoServices> getAllServices() {
        List<Services> services = servicesRepository.findAll();
        List<DtoServices> dtoServicesList = new ArrayList<>();
        for (Services service : services) {
            DtoServices dtoServices = serviceMapper.toDto(service);
            dtoServicesList.add(dtoServices);
        }
        return dtoServicesList;
    }

    @Override
    public DtoServices getServiceById(Long id) {
        Optional<Services> optional = servicesRepository.findById(id);
        if (optional.isPresent()) {
            DtoServices dtoServices = serviceMapper.toDto(optional.get());
            return dtoServices;
        }
        return null;
    }

    @Override
    public void deleteService(Long id) {
        Optional<Services> optional = servicesRepository.findById(id);
        if (optional.isPresent()) {
            servicesRepository.delete(optional.get());
        }
    }

    @Override
    public DtoServices updateService(Long id, Services services) {
        Optional<Services> optional = servicesRepository.findById(id);
        if (optional.isPresent()) {
            services.setId(id);
            Services dbServices = servicesRepository.save(services);
            return serviceMapper.toDto(dbServices);
        }
        return null;
    }
}
