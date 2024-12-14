package com.erciyes.service.impl;

import com.erciyes.model.EmailVerificationToken;
import com.erciyes.model.User;
import com.erciyes.repository.EmailVerificationTokenRepository;
import com.erciyes.repository.UserRepository;
import com.erciyes.service.IEmailVerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmailVerificationTokenServiceImpl implements IEmailVerificationTokenService {
    @Autowired
    private EmailVerificationTokenRepository tokenRepository;

    @Autowired
    UserRepository userRepository;

    // Token oluştur ve kaydet
    public EmailVerificationToken createVerificationToken(String email) {

        String token = generateVerificationCode(); // Rastgele 6 haneli kod oluştur
        EmailVerificationToken verificationToken = new EmailVerificationToken();
        Optional<User> optUser = userRepository.findByEmail(email);
        verificationToken.setUser(optUser.get());
        verificationToken.setToken(token);
//        verificationToken.setEmail(email);
        verificationToken.setExpiryDate(LocalDateTime.now().plusMinutes(10));

        // 10 dakika geçerli olacak
        return tokenRepository.save(verificationToken);
    }

    // Token oluşturma metodu
    private String generateVerificationCode() {
        int code = 100000 + (int) (Math.random() * 900000); // 6 haneli rastgele sayı
        return String.valueOf(code);
    }
}
