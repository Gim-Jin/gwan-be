package com.kimnjin.gwanyeon.review.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateReviewDto {

  private Long reviewId;
  private Long articleId;
  private Long writerId;
  private String content;
  private LocalDateTime updatedAt;
}
