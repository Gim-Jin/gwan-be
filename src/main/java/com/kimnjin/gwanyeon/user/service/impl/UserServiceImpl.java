package com.kimnjin.gwanyeon.user.service.impl;

import com.kimnjin.gwanyeon.user.dto.CreateUserRequestDto;
import com.kimnjin.gwanyeon.user.dto.SummaryUserDto;
import com.kimnjin.gwanyeon.user.dto.UpdateUserRequestDto;
import com.kimnjin.gwanyeon.user.dto.UserResponseDto;
import com.kimnjin.gwanyeon.user.entity.User;
import com.kimnjin.gwanyeon.user.repository.UserRepository;
import com.kimnjin.gwanyeon.user.service.UserService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Transactional
  @Override
  public UserResponseDto signUp(CreateUserRequestDto createUserRequestDto) {
    User user = createUserRequestDto.toEntity();
    int result = userRepository.insert(user);

    if (result == 0) {
      throw new IllegalArgumentException("회원 가입 실패");
    }

    return UserResponseDto.from(user);
  }

  @Transactional
  @Override
  public UserResponseDto updateUser(UpdateUserRequestDto updateUserRequestDto) {
    User existingUser = userRepository.findByLoginId(updateUserRequestDto.getLoginId());
    if (existingUser == null) {
      throw new IllegalArgumentException("대상없음");
    }

    existingUser.setNickname(updateUserRequestDto.getNickname());
    existingUser.setEmail(updateUserRequestDto.getEmail());
    existingUser.setPassword(updateUserRequestDto.getPassword());

    int result = userRepository.update(existingUser);
    if (result == 0) {
      throw new IllegalArgumentException("수정 실패");
    }

    return UserResponseDto.from(existingUser);
  }

  @Transactional
  @Override
  public void deleteUser(Long userId) {
    int result = userRepository.delete(userId);
    if (result == 0) {
      throw new IllegalArgumentException("삭제 실패");
    }
  }

  @Override
  public UserResponseDto getUser(Long userId) {
    return UserResponseDto.from(userRepository.findById(userId));
  }

  @Override
  public List<SummaryUserDto> getUsers() {
    List<User> userList = userRepository.selectAll();
    List<SummaryUserDto> userDtoList = new ArrayList<>();
    for (User user : userList) {
      userDtoList.add(SummaryUserDto.from(user));
    }
    return userDtoList;
  }
}
