package com.kimnjin.gwanyeon.exercisevideo.service.impl;


import com.kimnjin.gwanyeon.commons.exception.BadRequestException;
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
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO : 카테고리 별로 뽑아온다거나, 제목으로 뽑아오는 건 아직 구현 안함. 이건 프론트 나오는거 보고 할랭
@Service
@RequiredArgsConstructor
public class ExerciseVideoServiceImpl implements ExerciseVideoService {

  private final ExerciseVideoRepository exerciseVideoRepository;

  private final VideoTargetRepository videoTargetRepository;

  private final String NOT_FOUND = "영상을 찾을 수 없습니다.";

  private final String BAD_REQUEST = "잘못된 요청입니다.";


  @Override
  @Transactional
  public ExerciseVideoResponseDto createExerciseVideo(CreateExerciseVideoRequestDto dto) {

    ExerciseVideo exerciseVideo = dto.toEntity();

    if (exerciseVideoRepository.insert(exerciseVideo) == 0) {

      throw new BadRequestException(BAD_REQUEST);

    }

    Long videoId = exerciseVideo.getExerciseVideoId();

    VideoTarget videoTarget = new VideoTarget(videoId, dto.getTargetId());

    if (videoTargetRepository.insert(videoTarget) == 0) {

      throw new BadRequestException(BAD_REQUEST);

    }

    return ExerciseVideoResponseDto.from(exerciseVideo);

  }

  @Override
  @Transactional
  public ExerciseVideoResponseDto modifyExerciseVideo(ModifyExerciseVideoRequestDto dto, Long id) {

    ExerciseVideo existingExerciseVideo = exerciseVideoRepository.selectById(id);

    if (existingExerciseVideo == null) {

      throw new ResourceNotFoundException(id + NOT_FOUND);

    }

    existingExerciseVideo.setTitle(dto.getTitle());

    existingExerciseVideo.setDescription(dto.getDescription());

    int result = exerciseVideoRepository.update(existingExerciseVideo);

    if (result == 0) {

      throw new BadRequestException(BAD_REQUEST);

    }

    return ExerciseVideoResponseDto.from(existingExerciseVideo);
  }

  @Override
  @Transactional
  public void removeExerciseVideo(Long id) {

    int result = exerciseVideoRepository.deleteById(id);

    if (result == 0) {

      throw new ResourceNotFoundException(id + NOT_FOUND);

    }

  }

  @Override
  public ExerciseVideoResponseDto getExerciseVideo(Long id) {

    ExerciseVideo exerciseVideo = exerciseVideoRepository.selectById(id);

    if (exerciseVideo == null) {
      throw new ResourceNotFoundException(id + NOT_FOUND);
    }

    return ExerciseVideoResponseDto.from(exerciseVideo);

  }

  @Override
  public List<ExerciseVideoResponseDto> getAllExerciseVideo() {

    List<ExerciseVideo> videos = exerciseVideoRepository.selectAll();

    if (videos.isEmpty()) {
      return Collections.emptyList();
    }

    return videos.stream().map(
        ExerciseVideoResponseDto::from).toList();
  }


  // 이건 잠시 보류 -> 쿼리에서 처리할지, 서비스에서 처리할지.
  @Override
  public List<ExerciseVideoResponseDto> getExerciseVideoByKeyword(String keyword) {
    return List.of();
  }


  @Override
  public ExerciseVideoWithTargetResponseDto getExerciseVideoWithTarget(Long id) {
    ExerciseVideoWithTarget exerciseVideo = exerciseVideoRepository.selectByIdWithTarget(id);

    if (exerciseVideo == null) {
      throw new ResourceNotFoundException(id + NOT_FOUND);
    }

    return ExerciseVideoWithTargetResponseDto.from(exerciseVideo);
  }

  @Override
  public List<ExerciseVideoWithTargetResponseDto> getAllExerciseVideoWithTarget() {

    List<ExerciseVideoWithTarget> videos = exerciseVideoRepository.selectAllWithTargets();

    if (videos.isEmpty()) {
      return Collections.emptyList();
    }

    return videos.stream().map(ExerciseVideoWithTargetResponseDto::from).toList();
  }

  @Override
  public List<ExerciseVideoWithTargetResponseDto> getRankedExerciseVideo() {

    List<ExerciseVideoWithTarget> exerciseVideos = exerciseVideoRepository.selectNineVideosWithLikeCount();
    if (exerciseVideos.isEmpty()) {
      return Collections.emptyList();
    }

    return exerciseVideos.stream().map(ExerciseVideoWithTargetResponseDto::from).toList();
  }

  @Override
  public List<ExerciseVideoWithTargetResponseDto> searchVideos(String keyword, String target, String sort) {
    List<ExerciseVideoWithTarget> videos = exerciseVideoRepository.searchWithConditions(keyword, target, sort);
    return videos.stream().map(ExerciseVideoWithTargetResponseDto::from).toList();
  }
}

