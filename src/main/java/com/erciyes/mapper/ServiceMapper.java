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
        dtoServices.setBarberShop(service.getBarberShop());
        dtoServices.setDuration(service.getDuration());
        dtoServices.setPrice(service.getPrice());
        return dtoServices;
    }
}
