package com.kimnjin.gwanyeon.user.controller;

import com.kimnjin.gwanyeon.commons.dto.ApiResult;
import com.kimnjin.gwanyeon.user.dto.CreateUserRequestDto;
import com.kimnjin.gwanyeon.user.dto.UserResponseDto;
import com.kimnjin.gwanyeon.user.entity.User;
import com.kimnjin.gwanyeon.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @Operation(summary = "회원가입", description = "이후에는 토큰을 활용하게 된다면 token관련 패키지에서 진행될 여지 있음")
  @PostMapping
  public ResponseEntity<ApiResult<UserResponseDto>> createUser(
      @RequestBody CreateUserRequestDto createUserRequestDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResult.success(
            userService.signUp(createUserRequestDto),
            HttpStatus.CREATED.value(),
            "유저 등록 성공"
        ));
  }

  @Operation(summary = "아이디, 비밀번호 로그인",description = "이후 토큰으로 이동할 여지 있음")
  @PostMapping("/login")
  public ResponseEntity<ApiResult<UserResponseDto>> login(@RequestBody String loginId,@RequestBody String password) {
    UserResponseDto userResponseDto=userService.login(loginId, password);
    return ResponseEntity.status(HttpStatus.OK)
        .body(ApiResult.success(userResponseDto));
  }

  @GetMapping(/"mypage"/"{loginId}")
  public ResponseEntity<ApiResult<UserResponseDto>> mypage(@PathVariable("id") String loginId) {
    UserResponseDto userResponseDto = userService.
  }
}
