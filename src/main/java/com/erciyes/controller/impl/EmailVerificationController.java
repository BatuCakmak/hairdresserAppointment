package com.erciyes.controller.impl;

import com.erciyes.dto.DtoEmailVerificationToken;
import com.erciyes.model.EmailVerificationToken;
import com.erciyes.model.User;
import com.erciyes.repository.EmailVerificationTokenRepository;
import com.erciyes.repository.UserRepository;
import com.erciyes.service.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/email/verify")
public class EmailVerificationController {
    @Autowired
    private EmailVerificationTokenRepository emailVerificationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationServiceImpl authenticationService;


    @PostMapping("/check")
    public ResponseEntity<String> verifyCode(@RequestBody @Valid DtoEmailVerificationToken emailVerificationToken) {

        authenticationService.verify(emailVerificationToken);

        return ResponseEntity.ok("Doğrulama başarılı!");
    }
}
