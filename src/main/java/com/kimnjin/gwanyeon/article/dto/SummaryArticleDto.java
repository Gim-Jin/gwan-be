package com.kimnjin.gwanyeon.article.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SummaryArticleDto {

  private Long articleId;
  private Long userId;
  private String userName;
  private String title;
  private LocalDateTime createAt;

}
