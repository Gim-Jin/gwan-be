package com.kimnjin.gwanyeon.gpt.service.impl;

import com.kimnjin.gwanyeon.gpt.client.GptClient;
import com.kimnjin.gwanyeon.gpt.dto.GptMessageDto;
import com.kimnjin.gwanyeon.gpt.dto.GptRequestDto;
import com.kimnjin.gwanyeon.gpt.service.GptService;
import com.kimnjin.gwanyeon.gpt.util.PromptFormatter;
import com.kimnjin.gwanyeon.rehabprogram.dto.UserSurveyRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GptServiceImpl implements GptService {

  private final GptClient gptClient;
  private final PromptFormatter promptFormatter;

  @Override
  public String getGptResponse(UserSurveyRequestDto userSurveyRequestDto) {
    String prompt = promptFormatter.formatPrompt(userSurveyRequestDto);
    GptRequestDto gptRequestDto = new GptRequestDto("gpt-3.5-turbo",
        List.of(new GptMessageDto("user", prompt)));
    return gptClient.getCompletions(gptRequestDto);
  }
}
