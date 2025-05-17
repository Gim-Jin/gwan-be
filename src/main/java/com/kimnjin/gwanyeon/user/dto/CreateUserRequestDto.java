package com.kimnjin.gwanyeon.user.dto;

import com.kimnjin.gwanyeon.user.entity.User;
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
  private String userName;
  private String userNickname;
  private UserRole userRole;

  public User toEntity() {
    return User.builder()
        .userLoginId(this.userLoginId)
        .userPassword(this.userPassword)
        .userEmail(this.userEmail)
        .userNickname(this.userNickname)
        .userName(this.userName)
        .userRole(this.userRole)
        .build();
  }
}
