package com.kimnjin.gwanyeon.exercisevideo.dto;

import com.kimnjin.gwanyeon.exercisevideo.entity.ExerciseVideo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateExerciseVideoRequestDto {

  private Long userId;

  private String title;

  private String url;

  private String youtubeId;

  private String description;

  private Long targetId;

  public ExerciseVideo toEntity() {
    return ExerciseVideo.builder()
        .userId(this.userId)
        .title(this.title)
        .url(this.url)
        .youtubeId(this.youtubeId)
        .description(this.description)
        .build();
  }

}
