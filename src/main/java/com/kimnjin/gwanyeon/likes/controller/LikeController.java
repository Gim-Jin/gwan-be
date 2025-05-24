package com.kimnjin.gwanyeon.likes.controller;

import com.kimnjin.gwanyeon.commons.dto.ApiResult;
import com.kimnjin.gwanyeon.commons.security.UserPrincipal;
import com.kimnjin.gwanyeon.exercisevideo.dto.ExerciseVideoResponseDto;
import com.kimnjin.gwanyeon.likes.dto.CreateLikeDto;
import com.kimnjin.gwanyeon.likes.service.LikeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeController {

  private final LikeService likeService;
  private final String NO_CONTENT = "noContent";

  @GetMapping("/{videoId}")
  public ResponseEntity<ApiResult<Boolean>> isLike(@PathVariable Long videoId,
      @AuthenticationPrincipal UserPrincipal userPrincipal) {

    return ResponseEntity.ok(
        ApiResult.success(likeService.isLike(userPrincipal.getUserId(), videoId)));

  }

  @PostMapping
  public ResponseEntity<ApiResult<Boolean>> createLike(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestBody CreateLikeDto dto) {

    dto.setUserId(userPrincipal.getUserId());

    likeService.save(dto);

    return ResponseEntity.ok(ApiResult.success(true, 201, "created"));
  }

  @DeleteMapping("/{videoId}")
  public ResponseEntity<ApiResult<Boolean>> removeLike(
      @AuthenticationPrincipal UserPrincipal userPrincipal
      , @PathVariable Long videoId) {

    likeService.remove(userPrincipal.getUserId(), videoId);

    return ResponseEntity.ok(ApiResult.success(true));

  }

  @GetMapping
  public ResponseEntity<ApiResult<List<ExerciseVideoResponseDto>>> getLikesVideos(
      @AuthenticationPrincipal UserPrincipal userPrincipal
  ) {

    List<ExerciseVideoResponseDto> results = likeService.getLikesVideo(userPrincipal.getUserId());

    return !results.isEmpty() ? ResponseEntity.ok(ApiResult.success(results))
        : ResponseEntity.ok(ApiResult.success(results, 204, NO_CONTENT));

  }
}
