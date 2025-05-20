package com.kimnjin.gwanyeon.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TokenReissueRequestDto {

  private String accessToken;
  private String refreshToken;

  public TokenReissueRequestDto(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
}
