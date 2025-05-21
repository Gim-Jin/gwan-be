package com.kimnjin.gwanyeon.rehabprogram.service;

import com.kimnjin.gwanyeon.rehabprogram.dto.RehabProgramResponseDto;
import com.kimnjin.gwanyeon.rehabprogram.dto.UserSurveyRequestDto;

public interface RehabProgramService {

  RehabProgramResponseDto saveRehabProgram(UserSurveyRequestDto userSurveyRequestDto);

}
