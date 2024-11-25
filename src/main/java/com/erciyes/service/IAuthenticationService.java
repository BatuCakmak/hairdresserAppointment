package com.erciyes.service;

import com.erciyes.dto.AuthRequest;
import com.erciyes.dto.DtoUser;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest input);

}
