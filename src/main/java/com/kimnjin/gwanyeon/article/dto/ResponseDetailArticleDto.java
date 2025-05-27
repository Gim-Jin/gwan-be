package com.kimnjin.gwanyeon.article.dto;

import com.kimnjin.gwanyeon.article.entity.Article;
import com.kimnjin.gwanyeon.review.dto.ResponseReviewDto;
import com.kimnjin.gwanyeon.review.entity.Review;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDetailArticleDto {
  private Long articleId;
  private Long userId;
  private String userName;
  private String title;
  private String content;
  private List<ResponseReviewDto> reviews;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;


  public static ResponseDetailArticleDto from(Article article, Long userId, String userName, List<ResponseReviewDto> reviews) {
    ResponseDetailArticleDto responseDetailArticleDto = new ResponseDetailArticleDto();
    responseDetailArticleDto.articleId = article.getArticleId();
    responseDetailArticleDto.userId = userId;
    responseDetailArticleDto.userName = userName;
    responseDetailArticleDto.title = article.getTitle();
    responseDetailArticleDto.content = article.getContent();
    responseDetailArticleDto.reviews = reviews;
    responseDetailArticleDto.createTime = article.getCreatedAt();
    responseDetailArticleDto.updateTime = article.getUpdatedAt();
    return responseDetailArticleDto;
  }
}
