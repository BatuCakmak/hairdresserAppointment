package com.erciyes.jwt;

import com.erciyes.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JWTService {

    public static final String SECRET_KEY="Zm1tgWRhVFImbmxB0uGT53cufhp18E64E4rXkGjp1p0";

    /**
     * Kullanıcı bilgileriyle bir JWT token'ı oluşturur.
     * Token'ın içine 'userId', 'firstName' gibi ek bilgilerin yanı sıra
     * 'ROLE_' ön eki temizlenmiş rol listesini ('roles': ['ADMIN']) ekler.
     */
    public String generateToken(UserDetails userDetails){
        // 1. Spring Security'den gelen tüm yetkileri al (örn: "ROLE_ADMIN", "ADMIN_READ")
        List<String> authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        // 2. Sadece rol isimlerini al ve "ROLE_" ön ekini temizle. Frontend'in bu temiz isme ihtiyacı var.
        List<String> roleNames = authorities.stream()
                .filter(auth -> auth.startsWith("ROLE_")) // Sadece rollerle ilgilen
                .map(auth -> auth.substring(5))           // "ROLE_" kısmını kes
                .collect(Collectors.toList());

        // Token içine koyacağımız bilgileri (claims) hazırlıyoruz
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roleNames); // Temizlenmiş rol listesini token'a ekle

        // Diğer kullanıcı bilgilerini ekle
        if (userDetails instanceof User) {
            User user = (User) userDetails;
            claims.put("userId", user.getId());
            claims.put("firstName", user.getFirstName());
            claims.put("lastName", user.getLastName());
            claims.put("email", user.getEmail());
        }

        // Token'ı oluştur
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2)) // 2 saat geçerlilik
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Token'dan gelen temiz rol listesini ('roles': ['ADMIN']) alır ve Spring Security'nin
     * anlayacağı formata ('ROLE_ADMIN') çevirir. 403 hatasını bu metod düzeltir.
     */
    public Collection<SimpleGrantedAuthority> getAuthoritiesFromRoles(List<String> roles){
        if (roles == null) {
            return Collections.emptyList(); // Boş liste döndür, null olmasın
        }
        // --- BU KISIM GÜNCELLENDİ ---
        // Her rol isminin başına "ROLE_" ekleyerek Spring Security için yetki oluştur.
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    // --- Dosyanın geri kalanında bir değişiklik yok ---

    public <T> T exportToken(String token, Function<Claims,T> claimsFunc){
        Claims claims = getClaims(token);
        return claimsFunc.apply(claims);
    }

    public Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
    }

    public String getUsernameByToken(String token){
        return exportToken(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token){
        Date expireDate = exportToken(token, Claims::getExpiration);
        return new Date().before(expireDate);
    }

    public Key getKey(){
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    public List<String> getRolesFromToken(String token){
        Claims claims = getClaims(token);
        return claims.get("roles", List.class);
    }
}