package com.kimnjin.gwanyeon.comment.controller;

import com.kimnjin.gwanyeon.comment.dto.CommentResponseDto;
import com.kimnjin.gwanyeon.comment.dto.CreateCommentRequestDto;
import com.kimnjin.gwanyeon.comment.dto.ModifyCommentRequestDto;
import com.kimnjin.gwanyeon.comment.service.CommentService;
import com.kimnjin.gwanyeon.commons.dto.ApiResult;
import com.kimnjin.gwanyeon.commons.security.UserPrincipal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;
  private final String CREATED = "created";
  private final String DELETED = "deleted";
  private final String NO_CONTENT = "noContent";

  @GetMapping("/exercise-videos/{videoId}/comments")
  public ResponseEntity<ApiResult<List<CommentResponseDto>>> getAllCommentsByVideo(
      @PathVariable Long videoId) {

    List<CommentResponseDto> result = commentService.getAllCommentsByExerciseVideoId(videoId);

    return !result.isEmpty() ? ResponseEntity.ok(ApiResult.success(result))
        : ResponseEntity.ok(ApiResult.success(result, 204, NO_CONTENT));

  }

  @GetMapping("/exercise-videos/{videoId}/comments/{commentId}")
  public ResponseEntity<ApiResult<CommentResponseDto>> getCommentDetail(@PathVariable Long videoId,
      @PathVariable Long commentId) {

    CommentResponseDto result = commentService.getCommentById(commentId);

    return ResponseEntity.ok(ApiResult.success(result));
  }

  @PostMapping("/exercise-videos/{videoId}/comments")
  public ResponseEntity<ApiResult<CommentResponseDto>> createComment(
      @RequestBody CreateCommentRequestDto dto, @PathVariable Long videoId,
      @AuthenticationPrincipal UserPrincipal userPrincipal) {

    CommentResponseDto result = commentService.save(dto, videoId, userPrincipal.getUserId());

    return ResponseEntity.ok(ApiResult.success(result, 201, CREATED));
  }

  @DeleteMapping("/users/comments/{commentId}")
  public ResponseEntity<ApiResult<String>> deleteComment(@PathVariable Long commentId,
      @AuthenticationPrincipal UserPrincipal userPrincipal) {

    commentService.remove(commentId, userPrincipal);

    return ResponseEntity.ok(ApiResult.success(DELETED));
  }

  // 유저 입장에서의 Comment
  // TODO : 나중에 유저 다 되면, /users/me/comments로 바꾸고 뭐 아이디를 세션이나, 뭐 저기 토큰이나 이런곳에서 가져와서 처리해야함.
  @GetMapping("/users/comments")
  public ResponseEntity<ApiResult<List<CommentResponseDto>>> getAllCommentsByUserId(
      @AuthenticationPrincipal UserPrincipal userPrincipal) {

    List<CommentResponseDto> result = commentService.getAllCommentsByUserId(
        userPrincipal.getUserId());

    return !result.isEmpty() ? ResponseEntity.ok(ApiResult.success(result))
        : ResponseEntity.ok(ApiResult.success(result, 204, NO_CONTENT));
  }

  @PutMapping("/users/comments/{commentId}")
  public ResponseEntity<ApiResult<CommentResponseDto>> updateComment(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @PathVariable("commentId") Long commentId,
      @RequestBody ModifyCommentRequestDto dto
  ) {

    dto.setCommentId(commentId);
    dto.setUserId(userPrincipal.getUserId());
    return ResponseEntity.ok(
        ApiResult.success(commentService.modify(dto)));
  }

}
