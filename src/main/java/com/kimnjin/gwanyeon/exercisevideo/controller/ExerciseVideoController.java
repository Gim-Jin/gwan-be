package com.kimnjin.gwanyeon.exercisevideo.controller;


import com.kimnjin.gwanyeon.commons.dto.ApiResult;
import com.kimnjin.gwanyeon.exercisevideo.dto.CreateExerciseVideoRequestDto;
import com.kimnjin.gwanyeon.exercisevideo.dto.ExerciseVideoResponseDto;
import com.kimnjin.gwanyeon.exercisevideo.dto.ExerciseVideoWithTargetResponseDto;
import com.kimnjin.gwanyeon.exercisevideo.dto.ModifyExerciseVideoRequestDto;
import com.kimnjin.gwanyeon.exercisevideo.service.ExerciseVideoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exercise-video")
@RequiredArgsConstructor
public class ExerciseVideoController {

  private final ExerciseVideoService exerciseVideoService;

  @GetMapping
  public ResponseEntity<ApiResult<List<ExerciseVideoWithTargetResponseDto>>> getAllExerciseVideoWithTarget() {

    exerciseVideoService.getAllExerciseVideoWithTarget();
    
    return null;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResult<ExerciseVideoResponseDto>> getVideoDetail(@PathVariable long id) {

    exerciseVideoService.getExerciseVideoWithTarget(id);

    return null;
  }

  @PostMapping
  public ResponseEntity<ApiResult<ExerciseVideoResponseDto>> createExerciseVideoWithTarget(
      @RequestBody CreateExerciseVideoRequestDto requestDto) {

    exerciseVideoService.createExerciseVideo(requestDto);

    return null;
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResult<ExerciseVideoResponseDto>> modifyExerciseVideo(
      @PathVariable Long id, @RequestBody ModifyExerciseVideoRequestDto requestDto) {

    exerciseVideoService.modifyExerciseVideo(requestDto, id);

    return null;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResult<List<ExerciseVideoWithTargetResponseDto>>> deleteExerciseVideo(
      @PathVariable Long id) {

    exerciseVideoService.removeExerciseVideo(id);

    return null;
  }


}

