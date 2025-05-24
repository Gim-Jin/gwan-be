package com.kimnjin.gwanyeon.likes.service.impl;

import com.kimnjin.gwanyeon.commons.exception.BadRequestException;
import com.kimnjin.gwanyeon.exercisevideo.dto.ExerciseVideoResponseDto;
import com.kimnjin.gwanyeon.exercisevideo.entity.ExerciseVideo;
import com.kimnjin.gwanyeon.exercisevideo.repository.ExerciseVideoRepository;
import com.kimnjin.gwanyeon.likes.dto.CreateLikeDto;
import com.kimnjin.gwanyeon.likes.entity.Like;
import com.kimnjin.gwanyeon.likes.repository.LikeRepository;
import com.kimnjin.gwanyeon.likes.service.LikeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

  private final LikeRepository likeRepository;
  private final ExerciseVideoRepository exerciseVideoRepository;

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

  @Override
  public boolean isLike(Long userId, Long exerciseVideoId) {

    Like like = likeRepository.selectByUserIdAndVideoId(userId, exerciseVideoId);

    return like != null;
  }

  @Override
  public List<ExerciseVideoResponseDto> getLikesVideo(Long userId) {

    List<ExerciseVideo> videos = exerciseVideoRepository.selectLikesVideoByUserId(userId);

    return videos.stream().map(ExerciseVideoResponseDto::from).toList();

  }


}
