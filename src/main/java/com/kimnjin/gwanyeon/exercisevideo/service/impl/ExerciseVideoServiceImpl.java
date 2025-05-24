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
  public ExerciseVideoWithTargetResponseDto getExerciseVideoWithTarget(Long id) {
    ExerciseVideoWithTarget exerciseVideo = exerciseVideoRepository.selectByIdWithTarget(id);

    if (exerciseVideo == null) {
      throw new ResourceNotFoundException(id + NOT_FOUND);
    }

    return ExerciseVideoWithTargetResponseDto.from(exerciseVideo);
  }


  @Override
  public List<ExerciseVideoWithTargetResponseDto> searchVideos(String keyword, String target,
      String sort) {
    List<ExerciseVideoWithTarget> videos = exerciseVideoRepository.searchWithConditions(keyword,
        target, sort);
    return videos.stream().map(ExerciseVideoWithTargetResponseDto::from).toList();
  }


}

