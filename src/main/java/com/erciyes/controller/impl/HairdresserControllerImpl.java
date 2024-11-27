package com.erciyes.controller.impl;

import com.erciyes.controller.IHairdresserController;
import com.erciyes.dto.DtoHairdresser;
import com.erciyes.model.Hairdresser;
import com.erciyes.service.IHairdresserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hairdresser")
public class HairdresserControllerImpl implements IHairdresserController {

    @Autowired
    private IHairdresserService hairdresserService;

    @PostMapping("/create")
    @Override
    public DtoHairdresser createHairdresser(@RequestBody @Valid Hairdresser hairdresser) {
        return hairdresserService.createHairdresser(hairdresser);
    }

    @GetMapping("/list")
    @Override
    public List<DtoHairdresser> getAllHairdressers() {
        return hairdresserService.getAllHairdressers();
    }

    @GetMapping("/list/{id}")
    @Override
    public DtoHairdresser getHairdresserById(@PathVariable(name= "id") Long id) {
        return hairdresserService.getHairdresserById(id);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void deleteHairdresser(@PathVariable(name = "id") Long id) {
         hairdresserService.deleteHairdresser(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public DtoHairdresser updateHairdresser(@PathVariable(name = "id") Long id, @RequestBody @Valid Hairdresser hairdresser) {
        return hairdresserService.updateHairdresser(id, hairdresser);
    }
}
