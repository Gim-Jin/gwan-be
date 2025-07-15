package com.kimnjin.gwanyeon.recommand.dto;

import com.kimnjin.gwanyeon.recommand.entity.Recommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateRecommandDto {
  private Long userId;
  private Long articleId;

  public Recommand toEntity() {
    return Recommand.builder()
        .userId(userId)
        .articleId(articleId)
        .build();
  }

}
