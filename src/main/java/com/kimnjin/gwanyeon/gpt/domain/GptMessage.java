package com.kimnjin.gwanyeon.gpt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GptMessage {

  private String role;
  private String message;

  public GptMessage(String role, String message) {
    this.role = role;
    this.message = message;
  }

}
