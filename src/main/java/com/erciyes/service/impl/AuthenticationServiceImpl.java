package com.erciyes.service.impl;

import com.erciyes.dto.AuthRequest;
import com.erciyes.dto.AuthResponse;
import com.erciyes.dto.DtoUser;
import com.erciyes.exception.BaseException;
import com.erciyes.exception.ErrorMessage;
import com.erciyes.exception.MessageType;
import com.erciyes.jwt.JWTService;
import com.erciyes.model.RefreshToken;
import com.erciyes.model.User;
import com.erciyes.repository.RefreshTokenRepository;
import com.erciyes.repository.UserRepository;
import com.erciyes.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


    private User createUser(AuthRequest input){
        User user=new User();
        user.setCreateTime(new Date());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        //eklenecek  birşeyler
        return user;
    }

    private RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken=new RefreshToken();
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        return refreshToken;
    }


    @Override
    public DtoUser register(AuthRequest input) {
        User savedUser=userRepository.save(createUser(input));
        DtoUser dtoUser=new DtoUser();
        BeanUtils.copyProperties(savedUser,dtoUser);
        //beanutils yerine manuel kopyalama yapacağız ileride
        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest input) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(input.getUsername(),input.getPassword());
            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optUser =userRepository.findByUsername(input.getUsername());

            String accessToken=jwtService.generateToken(optUser.get());
            RefreshToken savedRefreshToken=refreshTokenRepository.save(createRefreshToken(optUser.get()));

            return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
        }
        catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID,e.getMessage()));
        }

    }


}
