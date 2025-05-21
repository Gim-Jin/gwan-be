package com.kimnjin.gwanyeon.gpt.client;

import com.kimnjin.gwanyeon.commons.exception.BadRequestException;
import com.kimnjin.gwanyeon.gpt.dto.GptRequestDto;
import com.kimnjin.gwanyeon.gpt.dto.GptResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GptClient {


  private final RestTemplate restTemplate;

  @Value("${OPEN_API_KEY}")
  private String apiKey;

  private static final String GPT_URL = "https://api.openai.com/v1/chat/completions";

  public String getCompletions(GptRequestDto gptRequestDto) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(apiKey);

    HttpEntity<GptRequestDto> entity = new HttpEntity<>(gptRequestDto, headers);

    ResponseEntity<GptResponseDto> response = restTemplate.exchange(
        GPT_URL,
        HttpMethod.POST,
        entity,
        GptResponseDto.class
    );
    if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
      throw new BadRequestException("응답이 실패하였거나, 바디가 존재하지 않음");
    }

    return response.getBody()
        .getChoices()
        .get(0)
        .getMessage()
        .getContent();
  }
}
