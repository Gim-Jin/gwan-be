package com.kimnjin.gwanyeon.exercisevideo.dto;

import com.kimnjin.gwanyeon.exercisevideo.entity.ExerciseVideo;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExerciseVideoResponseDto {

  Long exerciseVideoId;

  Long userId;

  String title;

  String url;

  String youtubeId;

  String description;

  LocalDateTime createdAt;

  LocalDateTime updatedAt;

  public static ExerciseVideoResponseDto from(ExerciseVideo exerciseVideo) {
    ExerciseVideoResponseDto responseDto = new ExerciseVideoResponseDto();

    responseDto.exerciseVideoId = exerciseVideo.getExerciseVideoId();
    responseDto.userId = exerciseVideo.getUserId();
    responseDto.title = exerciseVideo.getTitle();
    responseDto.url = exerciseVideo.getUrl();
    responseDto.youtubeId = exerciseVideo.getYoutubeId();
    responseDto.description = exerciseVideo.getDescription();
    responseDto.createdAt = exerciseVideo.getCreatedAt();
    responseDto.updatedAt = exerciseVideo.getUpdatedAt();


    return responseDto;
  }
}
