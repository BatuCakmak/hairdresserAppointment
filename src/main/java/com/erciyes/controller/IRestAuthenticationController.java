package com.erciyes.controller;

import com.erciyes.dto.AuthRequest;
import com.erciyes.dto.DtoUser;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest input);

}
