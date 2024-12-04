package com.erciyes.service.impl;

import com.erciyes.model.User;
import com.erciyes.repository.EmailVerificationTokenRepository;
import com.erciyes.repository.UserRepository;
import com.erciyes.service.IEmailVerificationTokenService;
import com.erciyes.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private EmailVerificationTokenRepository tokenRepository;
    @Autowired
    private IEmailVerificationTokenService tokenService;
    @Override
    public String sendMail(String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("berberrandevu0@gmail.com");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Berber Randevusu Icin Kod Dogrulama");
//        int randomSixDigit = (int)(Math.random() * 900000) + 100000;

        simpleMailMessage.setText(tokenService.createVerificationToken(email) + "   Gonderilen bu dogrulama kodunu giriniz!!!!");
        mailSender.send(simpleMailMessage);
        return "Gonderildi";
    }

    @Override
    public String sendMultiMedia() {
        return "";
    }


    private JavaMailSender mailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

}
