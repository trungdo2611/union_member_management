package com.laptrinhwebjava.QLDoanVien.jwt;

import com.laptrinhwebjava.QLDoanVien.security.customUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.SignatureException;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${com.laptrinhwebjava.QLDoanVien.jwt.secret}")
    private String JWT_SECRET;
    @Value("${com.laptrinhwebjava.QLDoanVien.jwt.expriration}")
    private int JWT_EXPIRATION;

    //tao jwt tu thong tin Doan vien
    public String generateToken(customUser user) {
        Date now = new Date();
        Date dateExpired = new Date(now.getTime() + JWT_EXPIRATION);
        //tao chuoi JWT tu email cua doan vien
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(dateExpired)
                .signWith(SignatureAlgorithm.HS512,JWT_SECRET)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        Claims claims =Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        }  catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

}
