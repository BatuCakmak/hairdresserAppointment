package com.erciyes.mapper;

import com.erciyes.dto.DtoAddress;
import com.erciyes.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public DtoAddress toDto(Address address) {
        if (address == null) return null;
        DtoAddress dtoAddress = new DtoAddress();
        dtoAddress.setCity(address.getCity());
        dtoAddress.setDistrict(address.getDistrict());
        return dtoAddress;
    }
}
