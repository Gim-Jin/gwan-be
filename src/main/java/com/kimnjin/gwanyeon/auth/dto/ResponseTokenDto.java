package com.kimnjin.gwanyeon.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ResponseTokenDto {

  private final String accessToken;
  private final String refreshToken;
  private final String userRole;

  public ResponseTokenDto(String accessToken, String refreshToken,String userRole) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.userRole = userRole;
  }
}
