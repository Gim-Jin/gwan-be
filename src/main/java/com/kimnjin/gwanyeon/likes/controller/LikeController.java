package com.kimnjin.gwanyeon.likes.controller;

import com.kimnjin.gwanyeon.commons.dto.ApiResult;
import com.kimnjin.gwanyeon.likes.dto.CreateLikeDto;
import com.kimnjin.gwanyeon.likes.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class LikeController {

  private final LikeService likeService;

  @PostMapping("/{userId}/likes")
  public ResponseEntity<ApiResult<Boolean>> getLike(@PathVariable Long userId,
      @RequestBody CreateLikeDto dto) {

    dto.setUserId(userId);

    likeService.save(dto);

    return ResponseEntity.ok(ApiResult.success(true, 201, "created"));
  }

  @DeleteMapping("/{userId}/likes/videos/{videoId}")
  public ResponseEntity<ApiResult<Boolean>> removeLike(@PathVariable Long userId
      , @PathVariable Long videoId) {

    likeService.remove(userId, videoId);

    return ResponseEntity.ok(ApiResult.success(true));

  }

}
