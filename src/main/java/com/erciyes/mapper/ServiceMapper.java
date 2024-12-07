package com.erciyes.mapper;

import com.erciyes.dto.DtoServices;
import com.erciyes.model.Services;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {
    public DtoServices toDto(Services service) {
        if (service == null) {
            return null;
        }
        DtoServices dtoServices = new DtoServices();
        dtoServices.setName(service.getName());
        dtoServices.setDuration(service.getDuration());
        dtoServices.setPrice(service.getPrice());
        dtoServices.setId(service.getId());
        dtoServices.setCreateTime(service.getCreateTime());
        return dtoServices;
    }
}
