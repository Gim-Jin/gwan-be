package com.kimnjin.gwanyeon.likes.service;

import com.kimnjin.gwanyeon.exercisevideo.dto.ExerciseVideoResponseDto;
import com.kimnjin.gwanyeon.likes.dto.CreateLikeDto;
import java.util.List;

public interface LikeService {

  boolean save(CreateLikeDto dto);

  boolean remove(Long userId, Long exerciseVideoId);

  boolean isLike(Long userId, Long exerciseVideoId);

  List<ExerciseVideoResponseDto> getLikesVideo(Long userId);

}
