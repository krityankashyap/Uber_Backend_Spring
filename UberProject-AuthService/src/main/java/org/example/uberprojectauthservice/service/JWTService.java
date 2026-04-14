package org.example.uberprojectauthservice.service;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService implements CommandLineRunner {

    @Value("${jwexpiryt}")
    private int expiry;

    @Value("${jwt.secret}")
    private String SECRET;

    /**
     * This method creates a brand-new token from the payload
     */

    private String createToken(Map<String, Object> payload, String username) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry);

        SecretKey key = Keys.hmacShaKeyFor(
                SECRET.getBytes(StandardCharsets.UTF_8)
        );

        return Jwts.builder()
                .setClaims(payload)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    @Override
    public void run(String... args) throws Exception {
        String username= "Krityan";
        Map<String , Object>mp = new HashMap<>();
        mp.put("email" , "kashyap@gamil.com");
        mp.put("password" , "a2345");

        String result= createToken(mp , username);
        System.out.println(result);
    }
}
