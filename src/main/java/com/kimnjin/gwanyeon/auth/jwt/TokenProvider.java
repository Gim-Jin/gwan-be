package com.kimnjin.gwanyeon.auth.jwt;


import com.kimnjin.gwanyeon.commons.exception.UnAuthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenProvider {

  @Value("${jwt.secret}")
  private String secretKeyPlain;

  @Value("${jwt.access-token-expiration-ms}")
  private long accessTokenExpiration;

  @Value("${jwt.refresh-token-expiration-ms}")
  private long refreshTokenExpiration;

  private Key key;

  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(secretKeyPlain.getBytes(StandardCharsets.UTF_8));
  }

  // 에세스 토큰 발급
  public String generateAccessToken(Long userId, String userNickName, String userRole) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + accessTokenExpiration);

    return Jwts.builder()
        .setSubject("access")
        .claim("userId", userId)
        .claim("userNickName", userNickName)
        .claim("userRole", userRole)
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(key, SignatureAlgorithm.HS512)
        .compact();
  }

  // 리프레시 토큰 발급
  public String generateRefreshToken(Long userId) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + refreshTokenExpiration);

    return Jwts.builder()
        .setSubject("refresh")
        .claim("userId", userId)
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(key, SignatureAlgorithm.HS512)
        .compact();
  }

  // 토큰 유효성 검사(만료 여부 올바른 형태인지)
  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token);
      return true;
    } catch (JwtException | UnAuthorizedException e) {
      return false;
    }
  }

  // claims추출 ||claims가 없는데?

  public Claims parseToken(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  // 각 요소별 추출
  public Long getUserIdFromToken(String token) {
    return parseToken(token).get("userId", Long.class);
  }

  public String getUserNickNameFromToken(String token) {
    return parseToken(token).get("userNickName", String.class);
  }

  public String getUserRoleFromToken(String token) {
    return parseToken(token).get("userRole", String.class);
  }

  // long으로 되어잇는 설정 시간을 LocalDateTime으로 변환
  public LocalDateTime getAccessTokenExpiryDate() {
    return new Date(System.currentTimeMillis() + accessTokenExpiration)
        .toInstant()//->인스턴스화
        .atZone(ZoneId.systemDefault())//-> 기본 시간대 (한국, seoul 등)
        .toLocalDateTime();//->시간으로 변화
  }

  public LocalDateTime getRefreshTokenExpiryDate() {
    return new Date(System.currentTimeMillis() + refreshTokenExpiration)
        .toInstant()//->인스턴스화
        .atZone(ZoneId.systemDefault())//-> 기본 시간대 (한국, seoul 등)
        .toLocalDateTime();//->시간으로 변화
  }
}
