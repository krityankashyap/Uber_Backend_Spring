package org.example.uberprojectauthservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${jwexpiryt}")
    private int expiry;

    @Value("${jwt.secret}")
    private String SECRET;

    /**
     * This method creates a brand-new token from the payload
     */

    private String createToken(Map<String, Object> payload, String email) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry);


        return Jwts.builder()
                .setClaims(payload)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .signWith(getSignKey())
                .compact();
    }


    private Claims extractAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClam(String token , Function<Claims , T>claimsResolver) {
        final Claims claim = extractAllClaims(token);
        return claimsResolver.apply(claim);
    }

    private Date getExpiration(String token){

        return extractClam(token , Claims :: getExpiration);
    }

    /**
     * This methods checks if the token expiry was before the current time stamp or not
     */

    private Boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }

    private String extractEmail(String token){
        return extractClam(token , Claims :: getSubject);
    }

    private Key getSignKey(){
        return Keys.hmacShaKeyFor(
                SECRET.getBytes(StandardCharsets.UTF_8)
        );
    }

    private Boolean validateToken(String token , String email){
        final String userEmailFetchedFromToken= extractEmail(token);

        return (userEmailFetchedFromToken.equals(email)) && !isTokenExpired(token);
    }
}
