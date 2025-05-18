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

  private final String CREATED = "created";
  private final String DELETED = "deleted";
  private final String NO_CONTENT = "noContent";
  
  private final ExerciseVideoService exerciseVideoService;

  @GetMapping
  public ResponseEntity<ApiResult<List<ExerciseVideoWithTargetResponseDto>>> getAllExerciseVideoWithTarget() {

    List<ExerciseVideoWithTargetResponseDto> result = exerciseVideoService.getAllExerciseVideoWithTarget();

    return !result.isEmpty() ? ResponseEntity.ok(ApiResult.success(result))
        : ResponseEntity.ok(ApiResult.success(result, 204, NO_CONTENT));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResult<ExerciseVideoResponseDto>> getVideoDetail(@PathVariable long id) {

    ExerciseVideoResponseDto result = exerciseVideoService.getExerciseVideoWithTarget(id);

    return ResponseEntity.ok(ApiResult.success(result));
  }

  @PostMapping
  public ResponseEntity<ApiResult<ExerciseVideoResponseDto>> createExerciseVideoWithTarget(
      @RequestBody CreateExerciseVideoRequestDto requestDto) {

    ExerciseVideoResponseDto result = exerciseVideoService.createExerciseVideo(requestDto);

    return ResponseEntity.ok(ApiResult.success(result, 201, CREATED));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResult<ExerciseVideoResponseDto>> modifyExerciseVideo(
      @PathVariable Long id, @RequestBody ModifyExerciseVideoRequestDto requestDto) {

    ExerciseVideoResponseDto result = exerciseVideoService.modifyExerciseVideo(requestDto, id);

    return ResponseEntity.ok(ApiResult.success(result));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResult<String>> deleteExerciseVideo(
      @PathVariable Long id) {

    exerciseVideoService.removeExerciseVideo(id);

    return ResponseEntity.ok(ApiResult.success(DELETED));
  }


}

