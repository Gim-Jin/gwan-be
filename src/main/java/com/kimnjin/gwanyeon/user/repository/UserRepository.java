package com.kimnjin.gwanyeon.user.repository;

import com.kimnjin.gwanyeon.user.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

  public int insert(User user);

  public int update(User user);

  public int delete(Long userId);

  User findById(Long userId);

  List<User> selectAll();

  User findByLoginId(String loginId);
}
