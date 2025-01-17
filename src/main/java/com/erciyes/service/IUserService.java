package com.erciyes.service;

import com.erciyes.dto.DtoHairdresser;
import com.erciyes.dto.DtoUser;
import com.erciyes.model.Hairdresser;
import com.erciyes.model.User;

import java.util.List;

public interface IUserService {

    public DtoUser createUser(User user);
    public List<DtoUser> getAllUsers();
    public DtoUser getUsersById(Long id);
    public void deleteUser(Long id);
    public DtoUser updateUser(Long id, User user);
}
