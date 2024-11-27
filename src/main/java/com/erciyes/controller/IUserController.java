package com.erciyes.controller;

import com.erciyes.dto.DtoUser;
import com.erciyes.model.User;

import java.util.List;

public interface IUserController {

    public DtoUser createUser(User user);
    public List<DtoUser> getAllUsers();
    public DtoUser getUsersById(Long id);
    public void deleteUser(Long id);
    public DtoUser updateUser(Long id, User user);
}
