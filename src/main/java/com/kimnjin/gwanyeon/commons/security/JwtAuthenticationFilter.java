package com.kimnjin.gwanyeon.commons.security;

import com.kimnjin.gwanyeon.auth.dto.TokenReissueRequestDto;
import com.kimnjin.gwanyeon.auth.dto.ResponseTokenDto;
import com.kimnjin.gwanyeon.auth.jwt.TokenProvider;
import com.kimnjin.gwanyeon.auth.service.AuthService;
import com.kimnjin.gwanyeon.commons.util.CookieUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final TokenProvider tokenProvider;
  private final CustomUserDetailsService customUserDetailsService;
  
  @Autowired
  private AuthService authService;

  public JwtAuthenticationFilter(TokenProvider tokenProvider, CustomUserDetailsService customUserDetailsService) {
    this.tokenProvider = tokenProvider;
    this.customUserDetailsService = customUserDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {

    String accessToken = resolveAccessToken(request);
    String refreshToken = resolveRefreshToken(request);

    // Access token이 유효한 경우
    if(accessToken != null && tokenProvider.validateToken(accessToken)) {
      authenticateUser(accessToken);
    } 
    // Access token이 없거나 유효하지 않고, refresh token이 있는 경우
    else if(refreshToken != null && tokenProvider.validateToken(refreshToken)) {
      try {
        // Refresh token으로 새로운 token 발급
        TokenReissueRequestDto reissueDto = new TokenReissueRequestDto(accessToken, refreshToken);
        ResponseTokenDto newTokens = authService.reissue(reissueDto);
        
        // 새로운 token들을 쿠키에 설정
        HttpHeaders headers = CookieUtil.createCookies(newTokens.getAccessToken(), newTokens.getRefreshToken());
        headers.forEach((name, values) -> {
          for (String value : values) {
            response.addHeader(name, value);
          }
        });
        
        // 새로운 access token으로 인증
        authenticateUser(newTokens.getAccessToken());
      } catch (Exception e) {
        // Refresh token 재발급 실패 시 로그만 남기고 계속 진행
        logger.error("Failed to reissue token: " + e.getMessage());
      }
    }

    filterChain.doFilter(request, response);
//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//    System.out.println("최종 SecurityContext Authentication: " + auth);
//    System.out.println("isAuthenticated: " + (auth != null && auth.isAuthenticated()));
//    System.out.println("Authorities: " + (auth != null ? auth.getAuthorities() : "null"));
  }
  
  private void authenticateUser(String token) {
    Long userId = tokenProvider.getUserIdFromToken(token);
    UserDetails userDetails = customUserDetailsService.loadUserByUsername(String.valueOf(userId));
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
//    System.out.println("==> 인증된 권한: " + authentication.getAuthorities());


  }

  private String resolveAccessToken(HttpServletRequest request) {
    // 1. 먼저 Authorization 헤더 확인
    String bearerToken = request.getHeader("Authorization");
    if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }

    // 2. 쿠키에서 토큰 확인
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ("accessToken".equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }

    return null;
  }
  
  private String resolveRefreshToken(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ("refreshToken".equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }
    return null;
  }
}
