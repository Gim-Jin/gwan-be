package com.kimnjin.gwanyeon.rehabprogram.service.impl;

import com.kimnjin.gwanyeon.rehabprogram.dto.RehabProgramResponseDto;
import com.kimnjin.gwanyeon.rehabprogram.dto.UserSurveyRequestDto;
import com.kimnjin.gwanyeon.rehabprogram.service.RehabProgramService;
import com.kimnjin.gwanyeon.rehabprogram.util.PromptFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RehabProgramServiceImpl implements RehabProgramService {

  private final OpenAiChatModel openAiChatModel;

  @Override
  public RehabProgramResponseDto saveRehabProgram(UserSurveyRequestDto userSurveyRequestDto) {

    String result = openAiChatModel.call(PromptFormatter.formatPrompt(userSurveyRequestDto));
    
    return null;

  }
}
