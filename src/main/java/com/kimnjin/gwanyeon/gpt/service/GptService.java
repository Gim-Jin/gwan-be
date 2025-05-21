package com.kimnjin.gwanyeon.gpt.service;

import com.kimnjin.gwanyeon.rehabprogram.dto.UserSurveyRequestDto;

public interface GptService {

  String getGptResponse(UserSurveyRequestDto userSurveyRequestDto);
  
}
