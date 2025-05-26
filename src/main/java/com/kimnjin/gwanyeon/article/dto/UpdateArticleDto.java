package com.kimnjin.gwanyeon.article.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateArticleDto {
  private Long articleId;
  private String title;
  private String content;
}
