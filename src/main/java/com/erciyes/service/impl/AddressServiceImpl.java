package com.erciyes.service.impl;

import com.erciyes.dto.DtoAddress;
import com.erciyes.mapper.AddressMapper;
import com.erciyes.model.Address;
import com.erciyes.repository.AddressRepository;
import com.erciyes.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressMapper addressMapper;

    @Override
    public DtoAddress createAddress(Address address) {
        address. setCreateTime(new Date());
        addressRepository.save(address);
        DtoAddress dtoAddress = addressMapper.toDto(address);
        return dtoAddress;
    }

    @Override
    public List<DtoAddress> getAddresses() {
        List<Address> addresses = addressRepository.findAll();
        List<DtoAddress> dtoAddresses = new ArrayList<>();
        for (Address address : addresses) {
            DtoAddress dtoAddress = addressMapper.toDto(address);
            dtoAddresses.add(dtoAddress);
        }
        return dtoAddresses;
    }

    @Override
    public DtoAddress getAddressesById(Long id) {
        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isPresent()) {
            DtoAddress dtoAddress = addressMapper.toDto(optional.get());
            return dtoAddress;
        }
        return null;
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public DtoAddress updateAddress(Long id, Address address) {
        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isPresent()) {
            address.setId(id);
            Address dbAddress = addressRepository.save(address);
            return addressMapper.toDto(dbAddress);
        }
        return null;
    }
}
