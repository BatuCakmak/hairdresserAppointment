package com.erciyes.service.impl;

import com.erciyes.dto.DtoUser;
import com.erciyes.mapper.UserMapper;
import com.erciyes.model.Hairdresser;
import com.erciyes.model.User;
import com.erciyes.repository.UserRepository;
import com.erciyes.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public DtoUser createUser(User user) {
        userRepository.save(user);
        DtoUser dtoUser = userMapper.toDto(user);
        return dtoUser;
    }

    @Override
    public List<DtoUser> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<DtoUser> dtoUserList = new ArrayList<>();
        for (User user:userList) {
            DtoUser dtoUser = userMapper.toDto(user);
            dtoUserList.add(dtoUser);
        }
        return dtoUserList;
    }

    @Override
    public DtoUser getUsersById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
             DtoUser dtoUser = userMapper.toDto(optional.get());
             return dtoUser;
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public DtoUser updateUser(Long id, User user) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user.setId(id);
            User dbUser = userRepository.save(user);
            return userMapper.toDto(dbUser);
        }
        return null;
    }
}
