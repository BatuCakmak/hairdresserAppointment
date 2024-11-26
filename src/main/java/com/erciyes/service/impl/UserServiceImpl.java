package com.erciyes.service.impl;

import com.erciyes.dto.DtoUser;
import com.erciyes.model.User;
import com.erciyes.repository.UserRepository;
import com.erciyes.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public DtoUser createUser(User user) {
        DtoUser dtoUser = new DtoUser();
        userRepository.save(user);
        BeanUtils.copyProperties(user, dtoUser);

        return dtoUser;
    }
}
