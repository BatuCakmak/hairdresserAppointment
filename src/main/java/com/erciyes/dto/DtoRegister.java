package com.erciyes.dto;

import com.erciyes.enums.Role;
import com.erciyes.model.Account;
import com.erciyes.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class DtoRegister {
    private String firstName;
    private String lastName;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private Date birthOfDate;
    private String tckn;
    @Email(message = "Gecerli bir email adresi giriniz", regexp = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|hotmail\\.com|outlook\\.com)$")
    private String email;
    private String phoneNumber;
    private Role role;

}
