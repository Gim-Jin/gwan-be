package com.kimnjin.gwanyeon.gpt.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GptResponseDto {

  public List<Choice> choices;

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Choice {

    private int index;
    private GptMessageDto message;
    private String finish_reason;
  }
}
