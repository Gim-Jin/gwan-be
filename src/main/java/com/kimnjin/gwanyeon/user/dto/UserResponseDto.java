package com.kimnjin.gwanyeon.user.dto;

import com.kimnjin.gwanyeon.user.entity.User;
import com.kimnjin.gwanyeon.user.entity.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {

  private String loginId;
  private String password;
  private String email;
  private String name;
  private String nickName;
  private UserRole role;

  public static UserResponseDto from(User user) {
    UserResponseDto responseDto = new UserResponseDto();
    responseDto.loginId = user.getLoginId();
    responseDto.password = user.getPassword();
    responseDto.email = user.getEmail();
    responseDto.name = user.getName();
    return responseDto;
  }
}
