package com.erciyes.jwt;

import com.erciyes.exception.BaseException;
import com.erciyes.exception.ErrorMessage;
import com.erciyes.exception.MessageType;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header=request.getHeader("Authorization");
        if (header==null){
            filterChain.doFilter(request,response);
            return;
        }
        String token;
        String username;

        token=header.substring(7);
        try {
           username= jwtService.getUsernameByToken(token);List<String> roles = jwtService.getRolesFromToken(token); // Rolleri çöz
            Collection<SimpleGrantedAuthority> authorities = jwtService.getAuthoritiesFromRoles(roles);

            if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=userDetailsService.loadUserByUsername(username);
             //  List<String> roles = jwtService.getRolesFromToken(token);

//               var authorities = jwtService.getAuthoritiesFromRoles(roles);

            if (userDetails!=null && jwtService.isTokenValid(token)){
//                   UsernamePasswordAuthenticationToken authenticationToken=new
//                           UsernamePasswordAuthenticationToken(username,null,authorities);
//
//                   authenticationToken.setDetails(userDetails);
//                   SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        username, null, authorities);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
                filterChain.doFilter(request, response);
            }
        }
        catch (ExpiredJwtException ex){
            throw new BaseException(new ErrorMessage(MessageType.TOKEN_IS_EXPIREDI, ex.getMessage()));
        }
        catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTİON,e.getMessage()));
        }
    }
}
