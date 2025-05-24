package com.kimnjin.gwanyeon.comment.dto;

import com.kimnjin.gwanyeon.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ModifyCommentRequestDto {

  private String content;
  private Long commentId;
  private Long userId;

  public Comment toEntity() {
    return Comment.builder()
        .content(this.content)
        .commentId(this.commentId)
        .userId(this.userId)
        .build();
  }

}
