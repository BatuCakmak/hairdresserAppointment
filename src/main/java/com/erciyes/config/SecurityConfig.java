package com.erciyes.config;

import com.erciyes.jwt.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.erciyes.enums.Permission.*;
import static com.erciyes.enums.Role.ADMIN;
import static com.erciyes.enums.Role.MANAGER;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF korumasını devre dışı bırak
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/authenticate", "/register", "/refreshToken", "/hairdresser/**","/appointment/available-slots","/appointment/**", "/user/**", "services/**","/address/**", "normal","payment/**","/barbershop/**", "email/verify/check").permitAll()

                        .requestMatchers("/admin/**").hasRole(ADMIN.name())

                        .requestMatchers(GET,"/admin/**").hasAuthority(ADMIN_READ.name() )
                        .requestMatchers(POST,"/admin/**").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT,"/admin/**").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE,"/admin/**").hasAuthority(ADMIN_DELETE.name())

                        .requestMatchers("/hairdresser/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                        .requestMatchers(HttpMethod.GET, "/user/list").hasRole(ADMIN.name())

                        .requestMatchers(GET,"/hairdresser/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                        .requestMatchers(POST,"/hairdresser/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                        .requestMatchers(PUT,"/hairdresser/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                        .requestMatchers(DELETE,"/hairdresser/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())

                        .anyRequest().authenticated() // Diğer tüm endpointler için oturum doğrulaması
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless oturum yönetimi
                )
                .authenticationProvider(authenticationProvider) // AuthenticationProvider tanımlaması
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // JWT filtre eklemesi

        return http.build();
    }

}
