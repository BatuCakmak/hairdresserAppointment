package com.erciyes.service.impl;

import com.erciyes.dto.AuthRequest;
import com.erciyes.dto.DtoUser;
import com.erciyes.model.User;
import com.erciyes.repository.UserRepository;
import com.erciyes.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private User createUser(AuthRequest input){
        User user=new User();
        user.setCreateTime(new Date());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        //eklenecek  birşeyler
        return user;
    }


    @Override
    public DtoUser register(AuthRequest input) {
        return null;
    }
}
