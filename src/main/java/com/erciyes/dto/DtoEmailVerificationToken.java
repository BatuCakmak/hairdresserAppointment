package com.erciyes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoEmailVerificationToken {
    @NotBlank(message = "E-posta boş olamaz!")
    @Email(message = "Geçersiz e-posta formatı!")
    private String email;

    @NotBlank(message = "Token boş olamaz!")
    private String token;
}
