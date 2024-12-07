package com.erciyes.controller.impl;

import com.erciyes.dto.DtoEmailVerificationToken;
import com.erciyes.model.EmailVerificationToken;
import com.erciyes.repository.EmailVerificationTokenRepository;
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

    @PostMapping("/check")
    public ResponseEntity<String> verifyCode(@RequestBody @Valid DtoEmailVerificationToken emailVerificationToken) {
        Optional<EmailVerificationToken> optToken = emailVerificationTokenRepository.findByEmail(emailVerificationToken.getEmail());

        if (emailVerificationToken.getToken() == null || !optToken.get().getToken().equals(emailVerificationToken.getToken())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Geçersiz kod!");
        }

        if (optToken.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kodun süresi dolmuş!");
        }

        return ResponseEntity.ok("Doğrulama başarılı!");
    }
}
