package com.kimnjin.gwanyeon.auth.controller;


import com.kimnjin.gwanyeon.auth.dto.CreateRequestDto;
import com.kimnjin.gwanyeon.auth.dto.LoginRequestDto;
import com.kimnjin.gwanyeon.auth.dto.ResponseTokenDto;
import com.kimnjin.gwanyeon.auth.dto.TokenReissueRequestDto;
import com.kimnjin.gwanyeon.auth.service.AuthService;
import com.kimnjin.gwanyeon.commons.dto.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  @Operation(summary = "회원가입", description = "유저 정보를 받아 유저db에 등록합니다.")
  @PostMapping("/signup")
  public ResponseEntity<ApiResult<Void>> signup(@RequestBody CreateRequestDto createRequestDto) {
    authService.signUp(createRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResult.success(null, 201, "회원가입 성공"));
  }

  @Operation(summary = "로그인", description = "아이디와 비밀번호를 통해 JWT를 반환")
  @PostMapping("/login")
  public ResponseEntity<ApiResult<ResponseTokenDto>> login(
      @RequestBody LoginRequestDto loginRequestDto) {
    ResponseTokenDto tokenDto = authService.login(loginRequestDto);

    ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", tokenDto.getRefreshToken())
        .httpOnly(true)
        .secure(true)
        .path("/")
        .maxAge(Duration.ofDays(7))
        .sameSite("Strict")
        .build();

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
        .body(ApiResult.success(new ResponseTokenDto(tokenDto.getAccessToken(), null), 200,
            "로그인 성공"));
  }

  @Operation(summary = "에세스 토큰 재발급", description = "토큰 만료시 리프래시 토큰을 활용하여 프론트엔드 axios에서 요청을 합니다")
  @PutMapping("/reissue")
  public ResponseEntity<ApiResult<ResponseTokenDto>> reissue(
      @CookieValue("refreshToken") String refreshToken,
      @RequestBody TokenReissueRequestDto tokenReissueRequestDto) {
    tokenReissueRequestDto.setRefreshToken(refreshToken);
    ResponseTokenDto tokenDto = authService.reissue(tokenReissueRequestDto);
    ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", tokenDto.getRefreshToken())
        .httpOnly(true)
        .secure(true)
        .path("/")
        .maxAge(Duration.ofDays(7))
        .sameSite("Strict")
        .build();

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
        .body(ApiResult.success(new ResponseTokenDto(tokenDto.getAccessToken(), null), 200,
            "재발급 성공"));

  }

  @Operation(summary = "로그아웃", description = "db의 리프레시 토큰을 삭제합니다.")
  @DeleteMapping("/logout")
  public ResponseEntity<ApiResult<Void>> logout(@CookieValue("refreshToken") String refreshToken) {
    authService.logout(refreshToken);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
