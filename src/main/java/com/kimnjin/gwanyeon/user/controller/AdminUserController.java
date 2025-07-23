package com.kimnjin.gwanyeon.user.controller;

import com.kimnjin.gwanyeon.commons.dto.ApiResult;
import com.kimnjin.gwanyeon.user.dto.SummaryUserDto;
import com.kimnjin.gwanyeon.user.dto.UpdateUserRequestDto;
import com.kimnjin.gwanyeon.user.dto.UserResponseDto;
import com.kimnjin.gwanyeon.user.repository.UserRepository;
import com.kimnjin.gwanyeon.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 토큰 처리 이후 구현 예정
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

  private final UserService userService;
  private final String DELETED = "deleted";

  @Operation(summary = "유저 전체 조회",description = "유저목록을 호출합니다.")
  @GetMapping("users")
  public ResponseEntity<ApiResult<List<SummaryUserDto>>> getUsers() {
//    System.out.println("==> getUsers 컨트롤러 진입");
    List<SummaryUserDto> userDtos = userService.getUsers();
    return ResponseEntity.status(HttpStatus.OK)
        .body(ApiResult.success(userDtos));
  }
  
  @Operation(summary = "관리자 권한으로 유저 수정", description = "유저의 권한, 닉네임을 변경합니다.")
  @PutMapping("/users/{userId}")
  public ResponseEntity<ApiResult<UserResponseDto>> editing(@PathVariable Long userId,
      @RequestBody UpdateUserRequestDto updateUserRequestDto) {
    UserResponseDto userResponseDto = userService.updateUserByAdmin(userId, updateUserRequestDto);
    return ResponseEntity.status(HttpStatus.OK)
        .body(ApiResult.success(userResponseDto));
  }

  @Operation(summary = "관리자 권한으로 유저 삭제", description = "관리자가 아닌 유저들을 삭제합니다.")
  @DeleteMapping("/users/{userId}")
  public ResponseEntity<ApiResult<String>> forceDelete(@PathVariable Long userId)
  {
    userService.deleteUserForced(userId);
    return ResponseEntity.ok(ApiResult.success(DELETED));
  }


}
