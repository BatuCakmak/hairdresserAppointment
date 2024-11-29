package com.erciyes.controller.impl;

import com.erciyes.controller.IAdminController;
import com.erciyes.dto.DtoAdmin;
import com.erciyes.model.Admin;
import com.erciyes.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminControllerImpl implements IAdminController {

    @Autowired
    IAdminService adminService;

    @PostMapping("/create")
    @Override
    public DtoAdmin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @GetMapping("/list")
    @Override
    public List<DtoAdmin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/list/{id}")
    @Override
    public DtoAdmin getAdminsById(@PathVariable(name = "id") Long id) {
        return adminService.getAdminsById(id);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void deleteAdmin(@PathVariable(name = "id") Long id) {
        adminService.deleteAdmin(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public DtoAdmin updateAdmin(@RequestBody @PathVariable(name = "id") Long id, Admin admin) {
        return adminService.updateAdmin(id, admin);
    }
}
