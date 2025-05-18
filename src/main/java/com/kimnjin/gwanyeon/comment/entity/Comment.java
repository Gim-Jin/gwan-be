package com.kimnjin.gwanyeon.comment.entity;


import com.kimnjin.gwanyeon.commons.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Comment extends BaseEntity {

  private Long commentId;

  private Long exerciseVideoId;

  private Long userId;

  private String content;


}
