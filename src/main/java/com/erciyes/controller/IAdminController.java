package com.erciyes.controller;

import com.erciyes.dto.DtoAdmin;
import com.erciyes.model.Admin;

import java.util.List;

public interface IAdminController {

    public DtoAdmin createAdmin(Admin admin);
    public List<DtoAdmin> getAllAdmins();
    public DtoAdmin getAdminsById(Long id);
    public void deleteAdmin(Long id);
    public DtoAdmin updateAdmin(Long id, Admin admin);
}
