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

  //r 근데 타겟이 없는
  ExerciseVideoResponseDto getExerciseVideo(Long id);

  // 전체 검색
  List<ExerciseVideoResponseDto> getAllExerciseVideo();

  // 제목 검색
  List<ExerciseVideoResponseDto> getExerciseVideoByTitle(String title);

  // r근데 타겟이 있는
  ExerciseVideoWithTargetResponseDto getExerciseVideoWithTarget(Long id);

  // 전체 검색 222
  List<ExerciseVideoWithTargetResponseDto> getAllExerciseVideoWithTarget();

  // 카테고리 검색 느낌?
  List<ExerciseVideoWithTargetResponseDto> getExerciseVideoWithTargetByTarget(String target);

  // 제목 검색 222
  List<ExerciseVideoWithTargetResponseDto> getExerciseVideoWithTargetByTitle(String title);


}
