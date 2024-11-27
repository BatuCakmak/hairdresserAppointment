package com.erciyes.controller;

import com.erciyes.dto.DtoAddress;
import com.erciyes.model.Address;

import java.util.List;

public interface IAddressController {

    public DtoAddress createAddress(Address address);
    public List<DtoAddress> getAddresses();
    public DtoAddress getAddressesById(Long id);
    public void deleteAddress(Long id);
    public DtoAddress updateAddress(Long id , Address address);
}
