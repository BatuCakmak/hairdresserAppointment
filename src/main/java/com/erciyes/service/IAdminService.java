package com.erciyes.service;

import com.erciyes.dto.DtoAdmin;
import com.erciyes.model.Admin;

import java.util.List;

public interface IAdminService {

    public DtoAdmin createAdmin(Admin admin);
    public List<DtoAdmin> getAllAdmins();
    public DtoAdmin getAdminsById(Long id);
    public void deleteAdmin(Long id);
    public DtoAdmin updateAdmin(Long id, Admin admin);
}
