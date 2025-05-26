package com.kimnjin.gwanyeon.review.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Review {

  private Long reivewId;
  private Long userId;
  private Long articleId;
  private String content;

}
