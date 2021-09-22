package com.example.TransportManagement.Utill;

import com.example.TransportManagement.entity.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public class JwtUtil {
    public static String generateToken(String signinKey, Integer userId, String subject,
                                       String userName, List<Role> authorities) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setSubject(subject).claim("userId", userId)
                .claim("Authorities", authorities)
                .claim("userName", userName).setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, "secret");

        return builder.compact();
    }

    public static String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public List<Role> getRoleNameFromJWT(String token, String signingKey) {
        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        return (List<Role>) claims.get("Authorities");
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(object);
    }

    public static String getUserIdFromJWT(String token, String signingKey) {
        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        return String.valueOf(claims.get("userId"));
    }

    public boolean validateToken(String authToken,String signingKey) {
        try {
            Jwts.parser().setSigningKey("secret").parseClaimsJws(authToken);

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserNameFromJWTs(String token, String signingKey) {
        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
        return String.valueOf(claims.get("userName"));
    }


    public Object getRoleFromJWT(String token, String signingKey) {
        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        return claims.get("roleName");
    }
}