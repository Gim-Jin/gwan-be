package com.kimnjin.gwanyeon.auth.service.Impl;

import com.kimnjin.gwanyeon.auth.dto.CreateRequestDto;
import com.kimnjin.gwanyeon.auth.dto.LoginRequestDto;
import com.kimnjin.gwanyeon.auth.dto.ResponseTokenDto;
import com.kimnjin.gwanyeon.auth.dto.TokenReissueRequestDto;
import com.kimnjin.gwanyeon.auth.entity.RefreshToken;
import com.kimnjin.gwanyeon.auth.jwt.TokenProvider;
import com.kimnjin.gwanyeon.auth.repository.AuthRepository;
import com.kimnjin.gwanyeon.auth.service.AuthService;
import com.kimnjin.gwanyeon.commons.exception.BadRequestException;
import com.kimnjin.gwanyeon.commons.exception.ResourceNotFoundException;
import com.kimnjin.gwanyeon.user.entity.User;
import com.kimnjin.gwanyeon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final AuthRepository authRepository;
  private final TokenProvider tokenProvider;
  private final PasswordEncoder passwordEncoder;


  @Transactional
  @Override
  public void signUp(CreateRequestDto createRequestDto) {
    if (userRepository.findByLoginId(createRequestDto.getLoginUserId()) != null) {
      throw new BadRequestException("ALREADY_EXISTS");
    }
    String pasword = passwordEncoder.encode(createRequestDto.getPassword());
    User user = createRequestDto.toEntity();
    user.setPassword(pasword);
    userRepository.insert(user);
  }

  @Transactional
  @Override
  public ResponseTokenDto login(LoginRequestDto loginRequestDto) {
    User user = userRepository.findByLoginId(loginRequestDto.getLoginUserId());

    if (user == null) {
      throw new ResourceNotFoundException("등록된 유저 아님");
    }

    if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
      throw new BadRequestException("INVALID_PASSWORD");
    }
    String accessToken = tokenProvider.generateAccessToken(
        user.getUserId(), user.getNickname(), user.getRole().name());

    String refreshToken = tokenProvider.generateRefreshToken(user.getUserId());

    RefreshToken existingRefreshToken = authRepository.findByRefreshToken(refreshToken);
    if (existingRefreshToken != null) {
      existingRefreshToken.updateToken(refreshToken, tokenProvider.getRefreshTokenExpiryDate());
      authRepository.update(existingRefreshToken);
    } else {
      RefreshToken tokenEntity = new RefreshToken(
          user.getUserId(),
          refreshToken,
          tokenProvider.getRefreshTokenExpiryDate()
      );

      authRepository.insert(tokenEntity);
    }


    return new ResponseTokenDto(accessToken, refreshToken, user.getRole().name());
  }

  @Transactional
  @Override
  public void logout(String refreshToken) {
    RefreshToken userRefreshToken = authRepository.findByRefreshToken(refreshToken);
    if (userRefreshToken == null) {
      throw new ResourceNotFoundException("이미 로그아웃됨, 유효하지 않은 토큰");
    }
    authRepository.delete(userRefreshToken);
  }

  @Transactional
  @Override
  public ResponseTokenDto reissue(TokenReissueRequestDto tokenReissueRequestDto) {
    String requestRefreshToken = tokenReissueRequestDto.getRefreshToken();
    if (!tokenProvider.validateToken(requestRefreshToken)) {
      throw new BadRequestException("INVALID_REFRESH_TOKEN");
    }

    RefreshToken tokenEntity = authRepository.findByRefreshToken(requestRefreshToken);
    if (tokenEntity == null) {
      throw new ResourceNotFoundException("NO_SUCH_REFRESH_TOKEN");
    }

    Long userId = tokenProvider.getUserIdFromToken(requestRefreshToken);
    User user = userRepository.findById(userId);
    if (user == null) {
      throw new ResourceNotFoundException("NO_SUCH_USER");
    }

    String newAccessToken = tokenProvider.generateAccessToken(
        user.getUserId(), user.getNickname(), user.getRole().name());

    String newRefreshToken = tokenProvider.generateRefreshToken(user.getUserId());

    tokenEntity.updateToken(newRefreshToken, tokenProvider.getRefreshTokenExpiryDate());
    authRepository.update(tokenEntity);

    return new ResponseTokenDto(newAccessToken, newRefreshToken, user.getRole().name());
  }
}
