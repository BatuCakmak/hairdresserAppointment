package com.erciyes.service.impl;

import com.erciyes.model.EmailVerificationToken;
import com.erciyes.repository.EmailVerificationTokenRepository;
import com.erciyes.service.IEmailVerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class EmailVerificationTokenServiceImpl implements IEmailVerificationTokenService {
    @Autowired
    private EmailVerificationTokenRepository tokenRepository;

    // Token oluştur ve kaydet
    public EmailVerificationToken createVerificationToken(String email) {
        String token = generateVerificationCode(); // Rastgele 6 haneli kod oluştur
        EmailVerificationToken verificationToken = new EmailVerificationToken(
                email,
                token,
                LocalDateTime.now().plusMinutes(10) // 10 dakika geçerli olacak
        );
        return tokenRepository.save(verificationToken);
    }

    // Token oluşturma metodu
    private String generateVerificationCode() {
        int code = 100000 + (int) (Math.random() * 900000); // 6 haneli rastgele sayı
        return String.valueOf(code);
    }
}
