package com.example.Dodo.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {

    @Value("1")
    private int expiration;

    @Value("gthhtkg")
    private String authKey;

    public String generateToken(Long userId, int expirationDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationDate);

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512, "authKey")
                .claim("role", "USER")
                .claim("id", userId)
                .compact();

    }


    public Long getUserIdFromToken(String token) throws SignatureException {
        try {
            Claims claims = Jwts.parser().setSigningKey(authKey).parseClaimsJws(token).getBody();
            return claims.get("id", Long.class);
        } catch (SignatureException e) {
            throw new SignatureException("Invalid JWT signature");
        }
    }
    public String generateAccessToken(Long userId) {
        return generateToken(userId, expiration);
    }

    public String generateRefreshToken(Long userId) {
        return generateToken(userId, expiration * 2);
    }

    public Long validateToken(String token) {

        try {
            Claims claims;
            claims = Jwts.parser().setSigningKey(authKey).parseClaimsJws(token).getBody();
            if(claims != null) {
                return Long.valueOf(String.valueOf(claims.get("claim")));
            } else {
                throw new RuntimeException("Токен пустой");
            }
        }catch (ExpiredJwtException e) {
            throw new RuntimeException("Срок действия токена истек. Просьба авторизоваться");
        }catch (MalformedJwtException e) {
            throw new RuntimeException("Токен взломан");
        }catch (ResponseStatusException e) {
            throw new RuntimeException("Токен пустой");
        }catch (Exception e) {
            throw new RuntimeException("Токен не прошел валидацию");
        }
    }
}
