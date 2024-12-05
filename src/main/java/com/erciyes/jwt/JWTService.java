package com.erciyes.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
public class JWTService {

    public static final String SECRET_KEY="Zm1tgWRhVFImbmxB0uGT53cufhp18E64E4rXkGjp1p0";

    public String generateToken(UserDetails userDetails){
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(auth -> auth.getAuthority())
                .toList();
        return Jwts.builder()
                 .setSubject(userDetails.getUsername())
                .claim("roles", roles)
                 .setIssuedAt(new Date())
                 .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2))
                 .signWith(getKey(), SignatureAlgorithm.HS256)
                 .compact();
    }

    public <T> T exportToken(String token, Function<Claims,T> claimsFunc){
        Claims claims=getClaims(token);
        return claimsFunc.apply(claims);
    }

    public Claims getClaims(String token){
        Claims claims=Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();

        return claims;
    }

    public String getUsernameByToken(String token){
        return exportToken(token,Claims::getSubject);
    }

    public boolean isTokenValid(String token){
        Date expireDate=exportToken(token,Claims::getExpiration);
        return new Date().before(expireDate);
    }


    public Key getKey(){
        byte bytes[]=Decoders.BASE64.decode((SECRET_KEY));
        return Keys.hmacShaKeyFor(bytes);
    }

    public List<String> getRolesFromToken(String token){
        Claims claims = getClaims(token); // Token içindeki veriyi çözümle
        return claims.get("roles", List.class); // Rolleri al
    }

    public Collection<SimpleGrantedAuthority> getAuthoritiesFromRoles(List<String> roles){
        return roles.stream()
                .map(SimpleGrantedAuthority::new).toList();
    }

}
