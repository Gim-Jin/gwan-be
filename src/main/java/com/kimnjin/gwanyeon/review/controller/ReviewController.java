package com.kimnjin.gwanyeon.review.controller;


import com.kimnjin.gwanyeon.commons.dto.ApiResult;
import com.kimnjin.gwanyeon.commons.security.UserPrincipal;
import com.kimnjin.gwanyeon.review.dto.CreateReviewDto;
import com.kimnjin.gwanyeon.review.dto.ResponseReviewDto;
import com.kimnjin.gwanyeon.review.dto.UpdateReviewDto;
import com.kimnjin.gwanyeon.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
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
public class ReviewController {

  private final ReviewService reviewService;

  @Operation(summary = "댓글 생성", description = "가입한 유저만 쓸 수 있습니다")
  @PostMapping("/articles/{articleId}/review")
  public ResponseEntity<ApiResult<ResponseReviewDto>> createReview(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @PathVariable Long articleId,
      @RequestBody CreateReviewDto createReviewDto
  ) {
    ResponseReviewDto responseReviewDto = reviewService.createReview(createReviewDto, articleId,
        userPrincipal.getUserId());
    return ResponseEntity.ok(ApiResult.success(responseReviewDto));
  }

  @Operation(summary = "댓글 수정", description = "작성자 본인만 수정 가능")
  @PutMapping("/articles/{articleId}/review")
  public ResponseEntity<ApiResult<ResponseReviewDto>> updateReview(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @PathVariable Long articleId,
      @RequestBody UpdateReviewDto updateReviewDto
  ) {

    ResponseReviewDto responseReviewDto = reviewService.updateReview(updateReviewDto,
        userPrincipal.getUserId(), articleId);
    return ResponseEntity.ok(ApiResult.success(responseReviewDto));
  }

  @Operation(summary = "댓글 삭제", description = "작성자 본인만 삭제 가능")
  @DeleteMapping("/articles/{articleId}/review/{reviewId}")
  public ResponseEntity<ApiResult<String>> removeReview(
      @PathVariable Long reviewId,
      @AuthenticationPrincipal UserPrincipal userPrincipal
  ) {
    reviewService.deleteReview(reviewId, userPrincipal.getUserId());
    return ResponseEntity.ok(ApiResult.success("삭제 성공"));
  }

  @Operation(summary = "유저 댓글들 조회", description = "작성자가 쓴 댓글 모음")
  @GetMapping("/users/reviews")
  public ResponseEntity<ApiResult<List<ResponseReviewDto>>> getReviews(
      @AuthenticationPrincipal UserPrincipal userPrincipal
  ) {
    List<ResponseReviewDto> reviews = reviewService.getReviewsByUserId(userPrincipal.getUserId());
    return ResponseEntity.ok(ApiResult.success(reviews));
  }
}
