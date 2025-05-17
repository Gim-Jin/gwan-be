package com.kimnjin.gwanyeon.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequestDto {
  private String userNickname;
  private String userEmail;
  private String userPassword;
  // String으로 받기 때문에 service단에서 올바른지 검증하는 기능필요
  private String userRole;
}
