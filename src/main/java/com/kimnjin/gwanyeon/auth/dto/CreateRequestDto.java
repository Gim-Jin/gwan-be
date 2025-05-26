package com.kimnjin.gwanyeon.auth.dto;

import com.kimnjin.gwanyeon.user.entity.User;
import com.kimnjin.gwanyeon.user.entity.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateRequestDto {

  private String loginUserId;
  private String password;
  private String email;
  private String name;
  private String nickname;
  private UserRole role;

  public User toEntity() {
    return User.builder()
        .loginId(this.loginUserId)
        .password(this.password)
        .email(this.email)
        .nickname(this.nickname)
        .name(this.name)
        .role(this.role)
        .build();
  }
}
