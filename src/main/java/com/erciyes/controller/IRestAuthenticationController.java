package com.erciyes.controller;

import com.erciyes.dto.*;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(DtoRegister register);

    public RootEntity<AuthResponse> authenticate(DtoLogin login);

}
