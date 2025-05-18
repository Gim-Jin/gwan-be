package com.kimnjin.gwanyeon.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserRequestDto {

  private String loginId;
  private String password;
}
