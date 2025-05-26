package com.kimnjin.gwanyeon.commons.util;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

public class CookieUtil {

  public static HttpHeaders createCookies(String accessToken, String refreshToken) {

    ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
        .httpOnly(true)
        .secure(false)
        .path("/")
        .maxAge(Duration.ofDays(7))
        .sameSite("Lax")
        .build();

    ResponseCookie accessCookie = ResponseCookie.from("accessToken", accessToken)
        .httpOnly(true)
        .secure(false)
        .path("/")
        .maxAge(Duration.ofMinutes(30))
        .sameSite("Lax")
        .build();

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    headers.add(HttpHeaders.SET_COOKIE, accessCookie.toString());

    return headers;
  }

  public static HttpHeaders cleanCookies() {
    ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", null)
        .httpOnly(true)
        .secure(false)
        .path("/")
        .maxAge(0)
        .sameSite("Lax")//Strict-> 젤빡셈 Lax -> 중간단계
        .build();

    ResponseCookie accessCookie = ResponseCookie.from("accessToken",null)
        .httpOnly(true)
        .secure(false)
        .path("/")
        .maxAge(0)
        .sameSite("Lax")
        .build();
    HttpHeaders header = new HttpHeaders();
    header.add(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    header.add(HttpHeaders.SET_COOKIE, accessCookie.toString());
    return header;
  }
}
