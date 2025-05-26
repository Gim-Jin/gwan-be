package com.kimnjin.gwanyeon.article.dto;

import com.kimnjin.gwanyeon.article.entity.Article;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ResponseArticleDto {
  private Long articleId;
  private Long userId;
  private String userName;
  private String title;
  private String content;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;

  public static ResponseArticleDto from(Article article, Long userId, String userName) {
    ResponseArticleDto responseArticleDto = new ResponseArticleDto();
    responseArticleDto.articleId = article.getArticleId();
    responseArticleDto.userId = userId;
    responseArticleDto.userName = userName;
    responseArticleDto.title = article.getTitle();
    responseArticleDto.content = article.getContent();
    responseArticleDto.createTime = article.getCreatedAt();
    responseArticleDto.updateTime = article.getUpdatedAt();
    return responseArticleDto;
  }
}
