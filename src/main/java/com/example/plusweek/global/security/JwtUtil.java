package com.example.plusweek.global.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtUtil {
    // Header Key 값
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // Token 식별자
    public static final String BEARER_PREFIX = "Bearer ";

    //토큰 만료시간
    private final long TOKEN_TIME = 60 * 60 * 1000L * 24;

    @Value("${jwt.secret.key}")
    private String secretKey;

    private Key key;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct // 1회만 실행되는 메소드
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes); // 우리가 가지고 있는 키로 사용할 키 만들기
    }


    public String createToken(String username) { // username으로 토큰 만들기
        Date date = new Date();

        return BEARER_PREFIX +
            Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(date.getTime() + TOKEN_TIME))
                .setIssuedAt(date)
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public String getJwtFromHeader(HttpServletRequest request) { // Header에서 Jwt 토큰 가져오기
        Cookie[] list = request.getCookies();
        String bearerToken = "";
        if (list == null) {
            return null;
        }
        for (Cookie cookie : list) {
            if (cookie.getName().equals(JwtUtil.AUTHORIZATION_HEADER)) {
                bearerToken = cookie.getValue();
            }
        }
        if (bearerToken.startsWith("Bearer")) { // 왜 막히지? StringUtils.hasText(bearerToken)
            return bearerToken.substring(9);
        }
        return null;
    }

    public boolean validateToken(String token) { // 토큰 검증
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

    public Claims getUserInfoFromToken(String token) { // 토큰에서 유저 정보 가져오기
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
