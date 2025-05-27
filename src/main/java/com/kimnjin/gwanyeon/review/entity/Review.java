package com.kimnjin.gwanyeon.review.entity;


import com.kimnjin.gwanyeon.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Review extends BaseEntity {

  private Long reviewId;
  private Long userId;
  private Long articleId;
  private String content;

  public void changeContent(String content) { this.content = content; }

}
