package com.kimnjin.gwanyeon.comment.dto;

import com.kimnjin.gwanyeon.comment.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentResponseDto {

  private Long commentId;

  private Long exerciseVideoId;

  private Long userId;

  private String content;

  LocalDateTime createdAt;

  LocalDateTime updatedAt;

  public static CommentResponseDto from(Comment comment) {

    CommentResponseDto dto = new CommentResponseDto();

    dto.setCommentId(comment.getCommentId());

    dto.setExerciseVideoId(comment.getExerciseVideoId());

    dto.setUserId(comment.getUserId());

    dto.setContent(comment.getContent());

    dto.setCreatedAt(comment.getCreatedAt());

    dto.setUpdatedAt(comment.getUpdatedAt());

    return dto;
  }
}
