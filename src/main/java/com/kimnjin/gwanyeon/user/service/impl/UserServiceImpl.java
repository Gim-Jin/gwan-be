package com.kimnjin.gwanyeon.user.service.impl;

import com.kimnjin.gwanyeon.auth.dto.LoginRequestDto;
import com.kimnjin.gwanyeon.comment.repository.CommentRepository;
import com.kimnjin.gwanyeon.commons.exception.ResourceNotFoundException;
import com.kimnjin.gwanyeon.exercisevideo.repository.ExerciseVideoRepository;
import com.kimnjin.gwanyeon.likes.repository.LikeRepository;
import com.kimnjin.gwanyeon.user.dto.MypageResponseDto;
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
  private final LikeRepository likeRepository;
  private final CommentRepository commentRepository;


  @Transactional
  @Override
  public UserResponseDto updateUser(Long userId, UpdateUserRequestDto updateUserRequestDto) {
    User existingUser = userRepository.findById(userId);
    if (existingUser == null) {
      throw new IllegalArgumentException("대상없음");
    }
    if (!existingUser.getLoginId().equals(updateUserRequestDto.getLoginId())) {
      throw new IllegalArgumentException("잘못된 접근");
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

  @Override
  public MypageResponseDto getMypage(Long userId) {
    User user = userRepository.findById(userId);
    if (user == null) {
      throw new ResourceNotFoundException("유저가 없습니다.");
    }
    int vCnt = likeRepository.selectByUserId(userId);
    int cCnt = commentRepository.selectAllByUserId(userId).size();
    MypageResponseDto mypageResponseDto = new MypageResponseDto();
    mypageResponseDto.setNickName(user.getNickname());
    mypageResponseDto.setEmail(user.getEmail());
    mypageResponseDto.setVideoCnt(vCnt);
    mypageResponseDto.setCommentCnt(cCnt);

    return mypageResponseDto;
  }

}
