package com.erciyes.controller.impl;

import com.erciyes.controller.IRestAuthenticationController;
import com.erciyes.controller.RestBaseController;
import com.erciyes.controller.RootEntity;
import com.erciyes.dto.*;
import com.erciyes.service.IAuthenticationService;
import com.erciyes.service.MailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;
    private MailService emailService; // EmailController'ı enjekte ediyoruz.

    // Constructor bazlı bağımlılık enjeksiyonu
    public RestAuthenticationControllerImpl(MailService emailService) {
        this.emailService = emailService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody DtoRegister register) {
        String email = register.getEmail();
        emailService.sendMail(email);
        return ok(authenticationService.register(register));
    }

    @PostMapping("/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody DtoLogin login) {
        return ok(authenticationService.authenticate(login));
    }
}
