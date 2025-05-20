package com.kimnjin.gwanyeon.auth.repository;

import com.kimnjin.gwanyeon.auth.entity.RefreshToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthRepository {

  public int insert(RefreshToken refreshToken);

  // 시간 갱신
  public int update(RefreshToken refreshToken);

  public int delete(RefreshToken refreshToken);

  public RefreshToken findByUserId(Long userId);

  public RefreshToken findByRefreshToken(String refreshToken);
}
