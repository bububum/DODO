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

    @Value("${expiration}")
    private int expiration;

    @Value("${authKey}")
    private String authKey;


    public String generateToken(Long userId, int expirationDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, expirationDate);

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512, "authKey")
//                .claim("role", "USER")
                .claim("id", userId)
                .compact();

    }


    public String generateAccessToken(Long userId) {
        return generateToken(userId, expiration);
    }

    public String generateRefreshToken(Long userId) {
        return generateToken(userId, expiration * 2);
    }

    public Long validateToken(String token, Integer languageOrdinal) {

        Language language = Language.getLanguage(languageOrdinal);

        try {
            Claims claims;
            claims = Jwts.parser().setSigningKey("authKey").parseClaimsJws(token).getBody();
            if(claims != null) {
                String userIdString = String.valueOf(claims.get("id"));
                return Long.parseLong(userIdString);
            } else {
                throw new RuntimeException(ResourceBundleLanguage.periodMessage(language, "tokenIsEmpty"));
            }
        }catch (ExpiredJwtException e) {
            throw new RuntimeException(ResourceBundleLanguage.periodMessage(language, "expiredToken"));
        }catch (MalformedJwtException e) {
            throw new RuntimeException(ResourceBundleLanguage.periodMessage(language, "hackedToken"));
        }catch (ResponseStatusException e) {
            throw new RuntimeException(ResourceBundleLanguage.periodMessage(language, "tokenIsEmpty"));
        }catch (Exception e) {
            throw new RuntimeException(ResourceBundleLanguage.periodMessage(language, "notValidatedToken"));
        }
    }

    public String getClaim(String token) {
        try {

            int i = token.lastIndexOf('.');
            String withoutSignature = token.substring(0, i + 1);

            Jwt<Header, Claims> untrusted = Jwts.parser().parseClaimsJwt(withoutSignature);

            Claims claims = untrusted.getBody();

            if (claims != null) {
                return String.valueOf(claims.get("claim"));
            } else {
                throw new RuntimeException("Токен пустой");
            }
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Срок действия токена истек. Просьба авторизоваться");
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Токен взломан");
        } catch (ResponseStatusException e) {
            throw new RuntimeException("Токен пустой");
        } catch (Exception e) {
            throw new RuntimeException("Токен не прошел валидацию");
        }
    }
}
