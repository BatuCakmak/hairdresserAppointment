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

        return dtoUser;
    }
}
