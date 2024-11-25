package com.erciyes.dto;

import com.erciyes.model.Account;
import com.erciyes.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;

import java.util.Date;

public class DtoRegister {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Date birthOfDate;
    private String tckn;


}
