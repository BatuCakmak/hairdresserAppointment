package com.erciyes.controller.impl;

import com.erciyes.controller.IAddressController;
import com.erciyes.dto.DtoAddress;
import com.erciyes.model.Address;
import com.erciyes.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressControllerImpl implements IAddressController {

    @Autowired
    IAddressService addressService;

    @Override
    @PostMapping("/save")
    public DtoAddress createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }

    @Override
    @GetMapping("/list")
    public List<DtoAddress> getAddresses() {
        return addressService.getAddresses();
    }

    @Override
    @GetMapping("/list/{id}")
    public DtoAddress getAddressesById(@PathVariable(name = "id") Long id) {
        return addressService.getAddressesById(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable(name = "id") Long id) {
        addressService.deleteAddress(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public DtoAddress updateAddress(@PathVariable(name = "id") @RequestBody Long id, Address address) {
        return addressService.updateAddress(id, address);
    }
}
