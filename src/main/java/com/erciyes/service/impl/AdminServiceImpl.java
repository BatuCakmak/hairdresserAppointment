package com.erciyes.service.impl;

import com.erciyes.dto.DtoAdmin;
import com.erciyes.mapper.AdminMapper;
import com.erciyes.model.Admin;
import com.erciyes.repository.AdminRepository;
import com.erciyes.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminMapper adminMapper;

    @Override
    public DtoAdmin createAdmin(Admin admin) {
        admin.setCreateTime(new Date());
        adminRepository.save(admin);
        DtoAdmin dtoAdmin = adminMapper.toDto(admin);
        return dtoAdmin;
    }

    @Override
    public List<DtoAdmin> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        List<DtoAdmin> dtoAdminList = new ArrayList<>();
        for (Admin admin : admins) {
            DtoAdmin dtoAdmin = adminMapper.toDto(admin);
            dtoAdminList.add(dtoAdmin);
        }
        return dtoAdminList;
    }

    @Override
    public DtoAdmin getAdminsById(Long id) {
        Optional<Admin> optional = adminRepository.findById(id);
        if (optional.isPresent()) {
            DtoAdmin dtoAdmin = adminMapper.toDto(optional.get());
            return dtoAdmin;
        }
        return null;
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public DtoAdmin updateAdmin(Long id, Admin admin) {
        Optional<Admin> optional = adminRepository.findById(id);
        if (optional.isPresent()) {
            admin.setId(id);
            Admin dbAdmin = adminRepository.save(admin);
            return adminMapper.toDto(dbAdmin);
        }
        return null;
    }
}
