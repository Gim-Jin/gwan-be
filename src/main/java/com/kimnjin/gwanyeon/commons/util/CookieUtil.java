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
}
