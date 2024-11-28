package com.erciyes.service;

import com.erciyes.dto.*;

public interface IAuthenticationService {

    public DtoUser register(DtoRegister register);

    public AuthResponse authenticate(DtoLogin login);

}
