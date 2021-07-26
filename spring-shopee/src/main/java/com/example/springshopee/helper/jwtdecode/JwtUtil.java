package com.example.springshopee.helper.jwtdecode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    static String SECRET = "1111111111111111111111";

    static long JWT_EXPIRATION = 604800000L;

    public static String generateToken(String userId, String role){
        Date expiredTime = new Date(System.currentTimeMillis() + JWT_EXPIRATION);
        Map map = new HashMap<>();
        map.put("userId", userId);
        map.put("role", role);
        String token = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setClaims(map)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiredTime)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return token;
    }

    public static Claims getAllClaimsFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public static Claims verifyToken(String token) {
        Claims data = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(token)
                .getBody();
        return data;
    }
}
