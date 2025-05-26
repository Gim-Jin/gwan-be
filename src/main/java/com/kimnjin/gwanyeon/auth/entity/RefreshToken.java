package com.kimnjin.gwanyeon.auth.entity;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

  private Long refreshTokenId;
  private Long userId;
  private String refreshToken;
  private LocalDateTime expiresAt;

  public RefreshToken(Long userId, String refreshToken, LocalDateTime expiresAt) {
    this.userId = userId;
    this.refreshToken = refreshToken;
    this.expiresAt = expiresAt;
  }

  public void updateToken(String refreshToken, LocalDateTime expiresAt) {
    this.refreshToken = refreshToken;
    this.expiresAt = expiresAt;
  }

}
