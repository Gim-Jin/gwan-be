package com.kimnjin.gwanyeon.gpt.domain;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GpetRequest {

  private String model;
  private List<GptMessage> messages;

  public GpetRequest(String model, List<GptMessage> messages) {
    this.model = model;
    this.messages = messages;
  }
}
