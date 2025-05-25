package com.kimnjin.gwanyeon.rehabprogram.service.impl;

import com.kimnjin.gwanyeon.commons.exception.BadRequestException;
import com.kimnjin.gwanyeon.commons.exception.ResourceNotFoundException;
import com.kimnjin.gwanyeon.rehabprogram.dto.PrescriptionResponseDto;
import com.kimnjin.gwanyeon.rehabprogram.dto.RehabProgramResponseDto;
import com.kimnjin.gwanyeon.rehabprogram.dto.UserSurveyRequestDto;
import com.kimnjin.gwanyeon.rehabprogram.entity.Part;
import com.kimnjin.gwanyeon.rehabprogram.entity.Prescription;
import com.kimnjin.gwanyeon.rehabprogram.entity.RehabProgram;
import com.kimnjin.gwanyeon.rehabprogram.repository.RehabProgramRepository;
import com.kimnjin.gwanyeon.rehabprogram.service.RehabProgramService;
import com.kimnjin.gwanyeon.rehabprogram.util.PromptFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RehabProgramServiceImpl implements RehabProgramService {

  private final OpenAiChatModel openAiChatModel;

  private final RehabProgramRepository rehabProgramRepository;

  private final String NOT_FOUND = "프로그램을 찾을 수 없습니다.";

  private final String BAD_REQUEST = "잘못된 요청입니다.";


  @Transactional
  @Override
  public RehabProgramResponseDto saveRehabProgram(UserSurveyRequestDto userSurveyRequestDto,
      Long userId) {
    String question = PromptFormatter.formatPrompt(userSurveyRequestDto);
    String result = openAiChatModel.call(question);
    RehabProgram newRehabProgram = RehabProgram.builder()
        .part(Part.valueOf(userSurveyRequestDto.getPainArea()))
        .userId(userId)
        .question(question)
        .prescription(result)
        .build();

    int saveResult = rehabProgramRepository.insert(newRehabProgram);

    if (saveResult == 0) {
      throw new BadRequestException(BAD_REQUEST);
    }

    return RehabProgramResponseDto.from(newRehabProgram);

  }

  @Override
  public RehabProgramResponseDto getRehabProgramById(Long id) {

    RehabProgram rehabProgram = rehabProgramRepository.selectRehabProgramByProgramId(id);

    if (rehabProgram == null) {
      throw new ResourceNotFoundException(NOT_FOUND);
    }

    return RehabProgramResponseDto.from(rehabProgram);

  }

  @Override
  public List<RehabProgramResponseDto> getAllRehabProgramsByUserId(Long userId) {

    List<RehabProgram> result = rehabProgramRepository.selectAllRehabProgramsByUserId(userId);

    if (result.isEmpty()) {
      return Collections.emptyList();
    }

    return result.stream().map(RehabProgramResponseDto::from).collect(Collectors.toList());
  }

  @Override
  public List<PrescriptionResponseDto> getAllPrescriptionsByUserId(Long userId) {

    List<Prescription> results = rehabProgramRepository.selectProgramDescriptionByUserId(userId);

    return results.stream().map(PrescriptionResponseDto::from).collect(Collectors.toList());

  }

  // 굳이 뭘 넘겨 줄 필요는 없는 것 같음.
  @Transactional
  @Override
  public void doneRehabProgram(Long rehabProgramId) {

    if (rehabProgramRepository.update(rehabProgramId) == 0) {
      throw new ResourceNotFoundException(NOT_FOUND);
    }

  }

  @Transactional
  @Override
  public void removeRehabProgram(Long rehabProgramId) {

    if (rehabProgramRepository.delete(rehabProgramId) == 0) {
      throw new ResourceNotFoundException(NOT_FOUND);
    }

  }

  @Override
  public List<PrescriptionResponseDto> getLatestPrescriptionsByUserId(Long userId) {
    List<Prescription> results = rehabProgramRepository.selectLatestProgramDescription(userId);

    return results.stream().map(PrescriptionResponseDto::from).collect(Collectors.toList());
  }
}
