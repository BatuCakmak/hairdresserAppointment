package com.erciyes.service;


import com.erciyes.dto.DtoServices;
import com.erciyes.model.Services;

import java.util.List;

public interface IServicesService {
    public DtoServices createService(Services services);
    public List<DtoServices> getAllServices();
    public DtoServices getServiceById(Long id);
    public void deleteService(Long id);
    public DtoServices updateService(Long id , Services services);
}
