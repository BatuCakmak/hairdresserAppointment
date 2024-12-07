package com.erciyes.mapper;

import com.erciyes.dto.DtoAdmin;
import com.erciyes.model.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    public DtoAdmin toDto(Admin admin) {
        if (admin == null) {return null;}
        DtoAdmin dtoAdmin = new DtoAdmin();
        dtoAdmin.setUserName(admin.getUserName());
        dtoAdmin.setId(admin.getId());
        dtoAdmin.setCreateTime(admin.getCreateTime());
        return dtoAdmin;
    }
}
