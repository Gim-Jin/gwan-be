package com.kimnjin.gwanyeon.user.controller;

import com.kimnjin.gwanyeon.commons.dto.ApiResult;
import com.kimnjin.gwanyeon.auth.dto.LoginRequestDto;
import com.kimnjin.gwanyeon.user.dto.UpdateUserRequestDto;
import com.kimnjin.gwanyeon.user.dto.UserResponseDto;
import com.kimnjin.gwanyeon.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final String DELETED = "deleted";

  private final UserService userService;


  @Operation(summary = "본인 정보 조회", description = "본인의 정보를 조회하여 수정 확인 가능")
  @GetMapping("/{userId}")
  public ResponseEntity<ApiResult<UserResponseDto>> getUser(@PathVariable Long userId) {

    UserResponseDto userResponseDto = userService.getUser(userId);
    return ResponseEntity.status(HttpStatus.OK)
        .body(ApiResult.success(userResponseDto));
  }

  @Operation(summary = "본인 정보 수정", description = "본인의 정보만 수정할 수 있음")
  @PutMapping("/{userId}")
  public ResponseEntity<ApiResult<UserResponseDto>> updateUser(@PathVariable Long userId,
      @RequestBody UpdateUserRequestDto updateUserRequestDto) {
    UserResponseDto userResponseDto = userService.updateUser(userId, updateUserRequestDto);
    return ResponseEntity.status(HttpStatus.OK)
        .body(ApiResult.success(userResponseDto));
  }

  @Operation(summary = "회원 탈퇴", description = "본인인지 토큰으로 확인하는 절차 필요")
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResult<String>> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok(ApiResult.success(DELETED));
// 무엇이 더 적절할까 
    //    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

//    return ResponseEntity.status(HttpStatus.NO_CONTENT)
//        .body(ApiResult.success(null));
  }

}
