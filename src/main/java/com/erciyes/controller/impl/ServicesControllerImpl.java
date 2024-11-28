package com.erciyes.controller.impl;

import com.erciyes.controller.IServicesController;
import com.erciyes.dto.DtoServices;
import com.erciyes.model.Services;
import com.erciyes.service.IServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesControllerImpl implements IServicesController {

    @Autowired
    IServicesService servicesService;

    @PostMapping("/create")
    @Override
    public DtoServices createService(@RequestBody Services services) {
        return servicesService.createService(services);
    }

    @GetMapping("/list")
    @Override
    public List<DtoServices> getAllServices() {
        return servicesService.getAllServices();
    }

    @GetMapping("/list/{id}")
    @Override
    public DtoServices getServiceById(@PathVariable(name = "id") Long id) {
        return servicesService.getServiceById(id);
    }

    @DeleteMapping("/delete")
    @Override
    public void deleteService(@PathVariable(name = "id") Long id) {
        servicesService.deleteService(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public DtoServices updateService(@PathVariable(name = "id") @RequestBody Long id, Services services) {
        return servicesService.updateService(id, services);
    }
}
