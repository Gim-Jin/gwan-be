package com.kimnjin.gwanyeon.auth.service;

import com.kimnjin.gwanyeon.auth.dto.CreateRequestDto;
import com.kimnjin.gwanyeon.auth.dto.LoginRequestDto;
import com.kimnjin.gwanyeon.auth.dto.ResponseTokenDto;
import com.kimnjin.gwanyeon.auth.dto.TokenReissueRequestDto;

public interface AuthService {

  void signUp(CreateRequestDto createRequestDto);

  ResponseTokenDto login(LoginRequestDto loginRequestDto);

  void logout(String refreshToken);

  // 에세스 만료시 업데이트
  public ResponseTokenDto reissue(TokenReissueRequestDto tokenReissueRequestDto);
}
