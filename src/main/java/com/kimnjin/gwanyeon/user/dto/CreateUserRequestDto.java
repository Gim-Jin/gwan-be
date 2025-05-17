package com.kimnjin.gwanyeon.user.dto;

import com.kimnjin.gwanyeon.user.entity.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequestDto {
  private String userLoginId;
  private String userPassword;
  private String userEmail;
  private String userNickname;
  private UserRole userRole;
}
