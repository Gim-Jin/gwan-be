package com.kimnjin.gwanyeon.user.repository;

import com.kimnjin.gwanyeon.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

  void save(User user);

  void update(User user);

  void delete(Long userId);

  User findById(Long userId);

  /**
   *  유저 전체조회 등은 이후 추가
   */
}
