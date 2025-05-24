package com.kimnjin.gwanyeon.user.service;

import com.kimnjin.gwanyeon.auth.dto.CreateRequestDto;
import com.kimnjin.gwanyeon.auth.dto.LoginRequestDto;
import com.kimnjin.gwanyeon.user.dto.MypageResponseDto;
import com.kimnjin.gwanyeon.user.dto.SummaryUserDto;
import com.kimnjin.gwanyeon.user.dto.UpdateUserRequestDto;
import com.kimnjin.gwanyeon.user.dto.UserResponseDto;
import java.util.List;

public interface UserService {


  public UserResponseDto updateUser(Long userId, UpdateUserRequestDto updateUserRequestDto);

  public void deleteUser(Long userId);

  public UserResponseDto getUser(Long userId);

  public List<SummaryUserDto> getUsers();

  public MypageResponseDto getMypage(Long userId);

}
