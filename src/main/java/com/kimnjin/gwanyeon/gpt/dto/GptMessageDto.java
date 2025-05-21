package com.kimnjin.gwanyeon.gpt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GptMessageDto {

  private String role;
  private String content;

  public GptMessageDto(String role, String content) {
    this.role = role;
    this.content = content;
  }

}
