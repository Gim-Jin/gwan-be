package com.kimnjin.gwanyeon.user.repository;

import com.kimnjin.gwanyeon.user.dto.CreateUserRequestDto;
import com.kimnjin.gwanyeon.user.dto.SummaryUserDto;
import com.kimnjin.gwanyeon.user.dto.UpdateUserRequestDto;
import com.kimnjin.gwanyeon.user.dto.UserResponseDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

  public int insert(CreateUserRequestDto createUserRequestDto);

  public int update(UpdateUserRequestDto updateUserRequestDto);

  public int delete(Long userId);

  UserResponseDto findById(Long userId);

  List<SummaryUserDto> selectAll();
}
