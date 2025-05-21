package com.kimnjin.gwanyeon.rehabprogram.controller;

import com.kimnjin.gwanyeon.commons.dto.ApiResult;
import com.kimnjin.gwanyeon.rehabprogram.dto.UserSurveyRequestDto;
import com.kimnjin.gwanyeon.rehabprogram.service.RehabProgramService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.scripting.defaults.RawLanguageDriver;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rehab-program")
@RequiredArgsConstructor
public class RehabProgramController {


  private final RehabProgramService rehabProgramService;

  @PostMapping
  public ResponseEntity<ApiResult<?>> createRoutines(
      @RequestBody UserSurveyRequestDto userSurveyRequestDto) {

    rehabProgramService.saveRehabProgram(userSurveyRequestDto);

    return null;
  }


}
