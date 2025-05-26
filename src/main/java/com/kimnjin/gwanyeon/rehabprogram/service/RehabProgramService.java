package com.kimnjin.gwanyeon.rehabprogram.service;

import com.kimnjin.gwanyeon.rehabprogram.dto.PrescriptionResponseDto;
import com.kimnjin.gwanyeon.rehabprogram.dto.RehabProgramResponseDto;
import com.kimnjin.gwanyeon.rehabprogram.dto.UserSurveyRequestDto;
import java.util.List;

public interface RehabProgramService {


  // 1. 글을 프로그램 루틴을 만들 수 있지 -> 인서트
  RehabProgramResponseDto saveRehabProgram(UserSurveyRequestDto userSurveyRequestDto, Long userId);

  // 4. 유저는 하나의 루틴을 골라서 조회할 수 있어야지.
  RehabProgramResponseDto getRehabProgramById(Long rehabProgramId);

  // 3. 유저는 자기가 질문을 던져서 만든 루틴들을 전체 조회 할 수 있지. -> 셀렉트 올
  List<RehabProgramResponseDto> getAllRehabProgramsByUserId(Long userId);


  List<PrescriptionResponseDto> getAllPrescriptionsByUserId(Long userId);

  // 4. 가장 최근의 완료 안된 루틴만 가져와
  List<PrescriptionResponseDto> getLatestPrescriptionsByUserId(Long userId);

  // 2. 루틴을 끝낼 수 있음 -> 업데이트
  void doneRehabProgram(Long rehabProgramId);

  // 5. 유저는 루틴을 삭제할 수 있어야 하나...???????
  void removeRehabProgram(Long rehabProgramId);

}
