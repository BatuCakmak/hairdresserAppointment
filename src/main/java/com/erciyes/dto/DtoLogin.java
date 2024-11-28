package com.erciyes.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoLogin {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
