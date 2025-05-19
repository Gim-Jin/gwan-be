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

  private Long userId;
  private String refreshToken;
  private LocalDateTime expiresAt;

  public void updateToken(String refreshToken, LocalDateTime expiresAt) {
    this.refreshToken = refreshToken;
    this.expiresAt = expiresAt;
  }

}
