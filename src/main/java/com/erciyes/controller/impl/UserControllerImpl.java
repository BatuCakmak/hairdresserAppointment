package com.erciyes.controller.impl;

import com.erciyes.controller.IUserController;
import com.erciyes.dto.DtoUser;
import com.erciyes.model.User;
import com.erciyes.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements IUserController {

    @Autowired
    IUserService userService;

    @Override
    @PostMapping("/create")
    public DtoUser createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @Override
    @GetMapping("/list")
    public List<DtoUser> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/list/{id}")
    @Override
    public DtoUser getUsersById(@PathVariable(name = "id") Long id) {
        return userService.getUsersById(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
    }

    @Override
    @PutMapping("/updete/{id}")
    public DtoUser updateUser(@PathVariable(name = "id") Long id, User user) {
        return userService.updateUser(id, user);
    }
}
