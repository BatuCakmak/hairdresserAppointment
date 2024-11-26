package com.erciyes.controller;

import com.erciyes.dto.DtoUser;
import com.erciyes.model.User;

public interface IUserController {

    public DtoUser createUser(User user);
}
