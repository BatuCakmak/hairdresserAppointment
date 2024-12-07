package com.erciyes.controller.impl;

import com.erciyes.controller.IBarberShopController;
import com.erciyes.dto.DtoBarberShop;
import com.erciyes.model.BarberShop;
import com.erciyes.service.IBarberShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/barbershop")
public class BarberShopControllerImpl implements IBarberShopController {
    @Autowired
    private IBarberShopService barberShopService;
    @PostMapping("/create")
    @Override
    public DtoBarberShop createBarberShop(@RequestBody @Valid BarberShop barberShop) {
        return barberShopService.createBarberShop(barberShop);
    }
    @GetMapping("/list")
    @Override
    public List<DtoBarberShop> getAllBarberShops() {
        return barberShopService.getAllBarberShops();
    }
    @GetMapping("{id}")
    @Override
    public DtoBarberShop getBarberShopById(@PathVariable(name= "id")Long id) {
        return barberShopService.getBarberShopById(id);
    }
    @DeleteMapping("/delete")
    @Override
    public void deleteBarberShop(@PathVariable(name = "id")Long id) {
        barberShopService.deleteBarberShop(id);
    }
    @PutMapping("/update")
    @Override
    public DtoBarberShop updateBarberShop(@PathVariable(name = "id") Long id, @RequestBody @Valid BarberShop barberShop) {
        return barberShopService.updateBarberShop(id, barberShop);
    }
}
