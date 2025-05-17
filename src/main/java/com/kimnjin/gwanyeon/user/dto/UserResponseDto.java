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

  private String userLoginId;
  private String userPassword;
  private String userEmail;
  private String userName;
  private String userNickName;
  private UserRole userRole;

  public static UserResponseDto from(User user) {
    UserResponseDto responseDto = new UserResponseDto();
    responseDto.userLoginId = user.getUserLoginId();
    responseDto.userPassword = user.getUserPassword();
    responseDto.userEmail = user.getUserEmail();
    responseDto.userName = user.getUserName();
    return responseDto;
  }
}
