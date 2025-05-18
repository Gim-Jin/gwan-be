package com.kimnjin.gwanyeon.likes.service.impl;

import com.kimnjin.gwanyeon.commons.exception.BadRequestException;
import com.kimnjin.gwanyeon.likes.dto.CreateLikeDto;
import com.kimnjin.gwanyeon.likes.entity.Like;
import com.kimnjin.gwanyeon.likes.repository.LikeRepository;
import com.kimnjin.gwanyeon.likes.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

  private final LikeRepository likeRepository;
  private final String BAD_REQUEST = "잘못된 요청입니다.";

  @Override
  @Transactional
  public boolean save(CreateLikeDto dto) {

    Like like = dto.toEntity();

    if (likeRepository.insert(like) == 0) {

      throw new BadRequestException(BAD_REQUEST);
    }

    return true;
  }

  @Override
  @Transactional
  public boolean remove(Long userId, Long exerciseVideoId) {

    Like like = Like.builder().userId(userId).exerciseVideoId(exerciseVideoId).build();

    if (likeRepository.delete(like) == 0) {
      throw new BadRequestException(BAD_REQUEST);
    }

    return true;

  }
}
