package com.kimnjin.gwanyeon.exercisevideo.dto;

import com.kimnjin.gwanyeon.exercisevideo.entity.ExerciseVideo;
import com.kimnjin.gwanyeon.exercisevideo.entity.ExerciseVideoWithTarget;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExerciseVideoWithTargetResponseDto extends ExerciseVideoResponseDto {

  List<String> targets;


  public static ExerciseVideoWithTargetResponseDto from(
      ExerciseVideoWithTarget exerciseVideoWithTarget) {

    ExerciseVideoWithTargetResponseDto responseDto = new ExerciseVideoWithTargetResponseDto();

    responseDto.exerciseVideoId = exerciseVideoWithTarget.getExerciseVideoId();

    responseDto.userId = exerciseVideoWithTarget.getUserId();

    responseDto.title = exerciseVideoWithTarget.getTitle();

    responseDto.url = exerciseVideoWithTarget.getUrl();

    responseDto.youtubeId = exerciseVideoWithTarget.getYoutubeId();

    responseDto.description = exerciseVideoWithTarget.getDescription();

    responseDto.createdAt = exerciseVideoWithTarget.getCreatedAt();

    responseDto.updatedAt = exerciseVideoWithTarget.getUpdatedAt();

    responseDto.targets = exerciseVideoWithTarget.getTargets();

    return responseDto;

  }

}
