package com.kimnjin.gwanyeon.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequestDto {

  private String nickname;
  private String loginId;
  private String email;
  private String password;
  private String role;

}
