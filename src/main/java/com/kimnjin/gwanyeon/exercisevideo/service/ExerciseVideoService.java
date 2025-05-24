package com.kimnjin.gwanyeon.exercisevideo.service;


import com.kimnjin.gwanyeon.exercisevideo.dto.CreateExerciseVideoRequestDto;
import com.kimnjin.gwanyeon.exercisevideo.dto.ExerciseVideoResponseDto;
import com.kimnjin.gwanyeon.exercisevideo.dto.ExerciseVideoWithTargetResponseDto;
import com.kimnjin.gwanyeon.exercisevideo.dto.ModifyExerciseVideoRequestDto;
import java.util.List;

public interface ExerciseVideoService {

  // cud
  ExerciseVideoResponseDto createExerciseVideo(CreateExerciseVideoRequestDto dto);

  ExerciseVideoResponseDto modifyExerciseVideo(ModifyExerciseVideoRequestDto dto, Long id);

  void removeExerciseVideo(Long id);

  // r근데 타겟이 있는
  ExerciseVideoWithTargetResponseDto getExerciseVideoWithTarget(Long id);

  List<ExerciseVideoWithTargetResponseDto> searchVideos(String keyword, String target, String sort);

}
