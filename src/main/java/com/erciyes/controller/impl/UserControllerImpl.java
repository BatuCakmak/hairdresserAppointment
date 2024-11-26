package com.erciyes.controller.impl;

import com.erciyes.controller.IUserController;
import com.erciyes.dto.DtoUser;
import com.erciyes.model.User;
import com.erciyes.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
