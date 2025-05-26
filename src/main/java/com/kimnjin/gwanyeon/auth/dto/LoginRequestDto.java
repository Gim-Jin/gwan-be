package com.kimnjin.gwanyeon.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDto {

  private String loginUserId;
  private String password;
}
