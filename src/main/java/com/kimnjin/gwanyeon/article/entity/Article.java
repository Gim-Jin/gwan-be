package com.kimnjin.gwanyeon.article.entity;


import com.kimnjin.gwanyeon.commons.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@AllArgsConstructor
public class Article extends BaseEntity {
  private Long articleId;
  private Long userId;
  private String title;
  private String content;

  public void changeTitle(String title) { this.title = title;}
  public void changeContent(String content) { this.content = content; }
}
