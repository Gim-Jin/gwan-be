package com.kimnjin.gwanyeon.comment.dto;

import com.kimnjin.gwanyeon.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateCommentRequestDto {


  private Long userId;

  private String content;

  public Comment toEntity() {

    return Comment.builder()
        .userId(this.userId)
        .content(content)
        .build();
  }
}
