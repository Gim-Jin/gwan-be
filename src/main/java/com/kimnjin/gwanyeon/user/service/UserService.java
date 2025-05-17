package com.kimnjin.gwanyeon.user.service;

import com.kimnjin.gwanyeon.user.dto.CreateUserRequestDto;
import com.kimnjin.gwanyeon.user.dto.SummaryUserDto;
import com.kimnjin.gwanyeon.user.dto.UpdateUserRequestDto;
import com.kimnjin.gwanyeon.user.dto.UserResponseDto;
import java.util.List;

public interface UserService {

  public UserResponseDto signUp(CreateUserRequestDto createUserRequestDto);

  public UserResponseDto updateUser(UpdateUserRequestDto updateUserRequestDto);

  public void deleteUser(Long userId);

  public UserResponseDto getUser(Long userId);

  public List<SummaryUserDto> getUsers();


}
