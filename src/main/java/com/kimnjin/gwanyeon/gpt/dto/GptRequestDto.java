package com.kimnjin.gwanyeon.gpt.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GptRequestDto {

  private String model;
  private List<GptMessageDto> messages;
  private double temperature = 0.7;

  public GptRequestDto(String model, List<GptMessageDto> messages) {
    this.model = model;
    this.messages = messages;
  }
}
