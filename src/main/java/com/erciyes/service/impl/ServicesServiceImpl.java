package com.erciyes.service.impl;

import com.erciyes.dto.DtoServices;
import com.erciyes.model.Services;
import com.erciyes.service.IServicesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesServiceImpl implements IServicesService {
    @Override
    public DtoServices createService(Services services) {
        return null;
    }

    @Override
    public List<DtoServices> getAllServices() {
        return List.of();
    }

    @Override
    public DtoServices getServiceById(Long id) {
        return null;
    }

    @Override
    public void deleteService(Long id) {

    }

    @Override
    public DtoServices updateService(Long id, Services services) {
        return null;
    }
}
