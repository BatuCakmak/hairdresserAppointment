package com.erciyes.controller.impl;

import com.erciyes.controller.IRestAuthenticationController;
import com.erciyes.controller.RestBaseController;
import com.erciyes.controller.RootEntity;
import com.erciyes.dto.*;
import com.erciyes.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody DtoRegister register) {
        return ok(authenticationService.register(register));
    }

    @PostMapping("/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody DtoLogin login) {
        return ok(authenticationService.authenticate(login));
    }
}
