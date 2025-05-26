package com.kimnjin.gwanyeon.comment.dto;

import com.kimnjin.gwanyeon.comment.entity.Comment;
import com.kimnjin.gwanyeon.comment.entity.CommentWithNickname;
import com.kimnjin.gwanyeon.comment.entity.CommentWithVideoTitleAndUrl;
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

  private String nickname;

  private String content;

  private String exerciseVideoTitle;

  private String youtubeUrl;

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


  public static CommentResponseDto fromWithNickname(CommentWithNickname comment) {

    CommentResponseDto dto = from(comment);

    dto.setNickname(comment.getNickname());

    return dto;
  }

  public static CommentResponseDto fromWithVideoTitle(CommentWithVideoTitleAndUrl comment) {

    CommentResponseDto dto = from(comment);

    dto.setExerciseVideoTitle(comment.getExerciseVideoTitle());

    dto.setYoutubeUrl(comment.getYoutubeId());

    return dto;
  }


}
