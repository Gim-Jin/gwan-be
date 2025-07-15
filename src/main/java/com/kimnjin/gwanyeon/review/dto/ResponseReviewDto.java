package com.kimnjin.gwanyeon.review.dto;

import com.kimnjin.gwanyeon.review.entity.Review;
import com.kimnjin.gwanyeon.user.entity.User;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseReviewDto {

  private Long reviewId;
  private Long userId;
  private Long articleId;
  private String writerNickName;
  private String userRole;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static ResponseReviewDto from(Review review, User user) {
    ResponseReviewDto dto = new ResponseReviewDto();
    dto.articleId = review.getArticleId();
    dto.reviewId = review.getReviewId();
    dto.userId = review.getUserId();
    dto.writerNickName = user.getNickname();
    dto.userRole = user.getRole().name();
    dto.content = review.getContent();
    dto.createdAt = review.getCreatedAt();
    dto.updatedAt = review.getUpdatedAt();
    return dto;
  }
}
