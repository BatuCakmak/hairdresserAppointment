package com.erciyes.service.impl;

import com.erciyes.dto.*;
import com.erciyes.enums.Role;
import com.erciyes.exception.BaseException;
import com.erciyes.exception.ErrorMessage;
import com.erciyes.exception.MessageType;
import com.erciyes.jwt.JWTService;
import com.erciyes.model.EmailVerificationToken;
import com.erciyes.model.RefreshToken;
import com.erciyes.model.User;
import com.erciyes.repository.EmailVerificationTokenRepository;
import com.erciyes.repository.RefreshTokenRepository;
import com.erciyes.repository.UserRepository;
import com.erciyes.service.IAuthenticationService;
import com.erciyes.service.MailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private MailService mailService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private EmailVerificationTokenRepository emailVerificationTokenRepository;



    private User createUser(DtoRegister input){
        User user=new User();
        user.setCreateTime(new Date());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setEmail(input.getEmail());
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setPhoneNumber(input.getPhoneNumber());
        user.setRole(Role.USER);
        user.setEnabled(false);

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
    public DtoUser register(DtoRegister register) {
        if(userRepository.existsByUsername(register.getUsername())){
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_ALREADY_EXIST,register.getUsername()));
        }
        if(userRepository.existsByEmail(register.getEmail())){
            throw new BaseException(new ErrorMessage(MessageType.EMAIL_ALREADY_EXIST,register.getEmail()));
        }

        User savedUser=userRepository.save(createUser(register));
        DtoUser dtoUser=new DtoUser();
        BeanUtils.copyProperties(savedUser,dtoUser);

        //beanutils yerine manuel kopyalama yapacağız ileride
        return dtoUser;
    }


    @Override
    public void verify(DtoEmailVerificationToken emailVerificationToken){
        Optional<EmailVerificationToken> optToken = emailVerificationTokenRepository.findByEmail(emailVerificationToken.getEmail());

        if (emailVerificationToken.getToken() == null || !optToken.get().getToken().equals(emailVerificationToken.getToken())) {
            throw new RuntimeException("Geçersiz kod!");
        }

        if (optToken.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Kodun süresi dolmuş");
        }

        EmailVerificationToken token = optToken.get();
        User user = token.getUser(); // Token üzerinden kullanıcıya erişim

        if (user == null) {
            throw new RuntimeException("Kullanici bulunamadi");
        }

        user.setEnabled(true); // Kullanıcıyı aktif hale getir
        userRepository.save(user); // Değişikliği kaydet

        // Token'ı artık kullanılamaz hale getirmek için silebilirsin
        emailVerificationTokenRepository.delete(token);

    }

    @Override
    public AuthResponse authenticate(DtoLogin login) {
        Optional<User> optUser =userRepository.findByUsername(login.getUsername());
        if (!optUser.get().isEnabled()) {

            throw new BaseException(new ErrorMessage(MessageType.USER_NOT_ENABLED, optUser.get().getUsername()));
        }
        try {
            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(login.getUsername(),login.getPassword());
            authenticationProvider.authenticate(authenticationToken);


            String accessToken=jwtService.generateToken(optUser.get());
            Optional<RefreshToken> refreshToken2=refreshTokenRepository.findByUserId(optUser.get().getId());
            if (refreshToken2.isPresent()){
                Optional<RefreshToken> optional=refreshTokenRepository.findByUserId(optUser.get().getId());

                RefreshToken refreshToken=optional.get();
                refreshTokenRepository.delete(refreshToken);
                RefreshToken refreshToken1=refreshTokenRepository.save(createRefreshToken(optUser.get()));

                return new AuthResponse(accessToken, refreshToken1.getRefreshToken());
            }
            RefreshToken savedRefreshToken=refreshTokenRepository.save(createRefreshToken(optUser.get()));
            return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());

        }
        catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID,e.getMessage()));
        }

    }


}
