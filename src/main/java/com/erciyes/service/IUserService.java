package com.erciyes.service;

import com.erciyes.dto.DtoUser;
import com.erciyes.model.User;

public interface IUserService {

    public DtoUser createUser(User user);
}
