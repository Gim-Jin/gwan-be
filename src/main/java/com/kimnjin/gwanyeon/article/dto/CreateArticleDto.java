package com.kimnjin.gwanyeon.article.dto;

import com.kimnjin.gwanyeon.article.entity.Article;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateArticleDto {
  private String title;
  private String content;

}
