package com.kimnjin.gwanyeon.likes.dto;

import com.kimnjin.gwanyeon.likes.entity.Like;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateLikeDto {

  private Long userId;
  private Long exerciseVideoId;

  public Like toEntity() {
    return Like.builder().userId(userId).exerciseVideoId(exerciseVideoId).build();
  }
}
