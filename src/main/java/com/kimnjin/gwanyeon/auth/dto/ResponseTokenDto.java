package com.kimnjin.gwanyeon.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ResponseTokenDto {

  private final String accessToken;
  private final String refreshToken;

  public ResponseTokenDto(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
}
