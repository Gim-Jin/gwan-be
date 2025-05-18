package com.kimnjin.gwanyeon.exercisevideo.service.impl;


import com.kimnjin.gwanyeon.commons.exception.BadRequestException;
import com.kimnjin.gwanyeon.commons.exception.NoContentException;
import com.kimnjin.gwanyeon.commons.exception.ResourceNotFoundException;
import com.kimnjin.gwanyeon.exercisevideo.dto.CreateExerciseVideoRequestDto;
import com.kimnjin.gwanyeon.exercisevideo.dto.ExerciseVideoResponseDto;
import com.kimnjin.gwanyeon.exercisevideo.dto.ExerciseVideoWithTargetResponseDto;
import com.kimnjin.gwanyeon.exercisevideo.dto.ModifyExerciseVideoRequestDto;
import com.kimnjin.gwanyeon.exercisevideo.entity.ExerciseVideo;
import com.kimnjin.gwanyeon.exercisevideo.entity.ExerciseVideoWithTarget;
import com.kimnjin.gwanyeon.exercisevideo.entity.VideoTarget;
import com.kimnjin.gwanyeon.exercisevideo.repository.ExerciseVideoRepository;
import com.kimnjin.gwanyeon.exercisevideo.repository.VideoTargetRepository;
import com.kimnjin.gwanyeon.exercisevideo.service.ExerciseVideoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseVideoServiceImpl implements ExerciseVideoService {

  private final ExerciseVideoRepository exerciseVideoRepository;

  private final VideoTargetRepository videoTargetRepository;

  @Override
  public ExerciseVideoResponseDto createExerciseVideo(CreateExerciseVideoRequestDto dto) {

    ExerciseVideo exerciseVideo = dto.toEntity();

    if(exerciseVideoRepository.insert(exerciseVideo) == 0) {

      throw new BadRequestException("잘못된 요청입니다.");

    }

    Long videoId = exerciseVideo.getExerciseVideoId();

    VideoTarget videoTarget = new VideoTarget(videoId, dto.getTargetId());

    if(videoTargetRepository.insert(videoTarget) == 0) {

      throw new BadRequestException("잘못된 요청입니다.");

    };

    return ExerciseVideoResponseDto.from(exerciseVideo);

  }

  @Override
  public ExerciseVideoResponseDto modifyExerciseVideo(ModifyExerciseVideoRequestDto dto, Long id) {

    ExerciseVideo existingExerciseVideo = exerciseVideoRepository.selectById(id);

    if(existingExerciseVideo == null) {

      throw new ResourceNotFoundException(id+"영상을 찾을 수 없습니다.");

    }

    existingExerciseVideo.setTitle(dto.getTitle());

    existingExerciseVideo.setDescription(dto.getDescription());

    int result = exerciseVideoRepository.update(existingExerciseVideo);

    if(result == 0) {

      throw new BadRequestException("잘못된 요청입니다.");

    }

    return ExerciseVideoResponseDto.from(existingExerciseVideo);
  }

  @Override
  public void removeExerciseVideo(Long id) {

    int result = exerciseVideoRepository.deleteById(id);

    if(result == 0) {

      throw new ResourceNotFoundException(id+"영상을 찾을 수 없습니다.");

    }

  }

  @Override
  public ExerciseVideoResponseDto getExerciseVideo(Long id) {

    ExerciseVideo exerciseVideo = exerciseVideoRepository.selectById(id);

    if(exerciseVideo == null) {
      throw new ResourceNotFoundException(id+"영상을 찾을 수 없습니다.");
    }

    return ExerciseVideoResponseDto.from(exerciseVideo);

  }

  @Override
  public List<ExerciseVideoResponseDto> getAllExerciseVideo() {

    List<ExerciseVideo> videos = exerciseVideoRepository.selectAll();

    if(videos.isEmpty()) {
      throw new NoContentException("영상이 없습니다.");
    }

    return videos.stream().map(
        ExerciseVideoResponseDto::from).toList();
  }


  // 이건 잠시 보류 -> 쿼리에서 처리할지, 서비스에서 처리할지.
  @Override
  public List<ExerciseVideoResponseDto> getExerciseVideoByTitle(String title) {
    return List.of();
  }


  @Override
  public ExerciseVideoWithTargetResponseDto getExerciseVideoWithTarget(Long id) {
    ExerciseVideoWithTarget exerciseVideo = exerciseVideoRepository.selectByIdWithTarget(id);

    if(exerciseVideo == null) {
      throw new ResourceNotFoundException(id+"영상을 찾을 수 없습니다.");
    }

    return ExerciseVideoWithTargetResponseDto.from(exerciseVideo);
  }

  @Override
  public List<ExerciseVideoWithTargetResponseDto> getAllExerciseVideoWithTarget() {

    List<ExerciseVideoWithTarget> videos = exerciseVideoRepository.selectAllWithTargets();

    if(videos.isEmpty()) {

      throw new NoContentException("영상이 없습니다.");

    }

    return videos.stream().map(ExerciseVideoWithTargetResponseDto::from).toList();
  }

  // 이건 잠시 보류 -> 쿼리에서 처리할지, 서비스에서 처리할지.
  @Override
  public List<ExerciseVideoWithTargetResponseDto> getExerciseVideoWithTargetByTarget(
      String target) {
    return List.of();
  }

  // 이건 잠시 보류 -> 쿼리에서 처리할지, 서비스에서 처리할지.
  @Override
  public List<ExerciseVideoWithTargetResponseDto> getExerciseVideoWithTargetByTitle(String title) {
    return List.of();
  }
}
