package com.erciyes.service;

import com.erciyes.model.EmailVerificationToken;

public interface IEmailVerificationTokenService {
    public EmailVerificationToken createVerificationToken(String email);
}
