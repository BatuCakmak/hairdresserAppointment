package com.erciyes.mapper;

import com.erciyes.dto.DtoHairdresser;
import com.erciyes.dto.DtoUser;
import com.erciyes.model.Hairdresser;
import com.erciyes.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public DtoUser toDto(User user) {
        if (user == null) {
            return null;
        }
        DtoUser dtoUser = new DtoUser();
        dtoUser.setFirstName(user.getFirstName());
        dtoUser.setLastName(user.getLastName());
        dtoUser.setEmail(user.getEmail());
        dtoUser.setPassword(user.getPassword());
        dtoUser.setPhoneNumber(user.getPhoneNumber());
        dtoUser.setUsername(user.getUsername());
        dtoUser.setId(user.getId());
        dtoUser.setCreateTime(user.getCreateTime());
        dtoUser.setEnable(user.isEnabled());
        if (user.getRole() != null) {
            dtoUser.setRole(user.getRole().name()); // örn: Role.ADMIN -> "ADMIN"
        } else {
            dtoUser.setRole("ROL YOK"); // Veya null bırakabilirsiniz
        }
        return dtoUser;
    }
}
