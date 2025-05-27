package com.kimnjin.gwanyeon.recommand.controller;


import com.kimnjin.gwanyeon.article.dto.SummaryArticleDto;
import com.kimnjin.gwanyeon.commons.dto.ApiResult;
import com.kimnjin.gwanyeon.commons.security.UserPrincipal;
import com.kimnjin.gwanyeon.recommand.dto.CreateRecommandDto;
import com.kimnjin.gwanyeon.recommand.service.RecommandService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/recommand")
public class RecommandController {

  private final RecommandService recommandService;

  @Operation(summary = "게시글 추천했는지 확인")
  @GetMapping("/{articeid}")
  public ResponseEntity<ApiResult<Boolean>> isRecommand(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @PathVariable Long articeid) {

    return ResponseEntity.ok(
        ApiResult.success(recommandService.isRecommand(userPrincipal.getUserId(), articeid))
    );
  }

  @Operation(summary = "추천")
  @PostMapping
  public ResponseEntity<ApiResult<Boolean>> createRecommand(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestBody CreateRecommandDto createRecommandDto
  ){
    createRecommandDto.setUserId(userPrincipal.getUserId());

    recommandService.save(createRecommandDto);
    return ResponseEntity.ok(ApiResult.success(true,201,"created"));
  }

  @Operation(summary = "내가 추천 누른 게시글")
  @GetMapping
  public ResponseEntity<ApiResult<List<SummaryArticleDto>>> getRecommandArticles(
      @AuthenticationPrincipal UserPrincipal userPrincipal
  ){
    List<SummaryArticleDto> articles = recommandService.getRecommandArticle(userPrincipal.getUserId());

    return !articles.isEmpty() ? ResponseEntity.ok(ApiResult.success(articles))
        : ResponseEntity.ok(ApiResult.success(articles, 204, "없음"));
  }

  @Operation(summary = "추천 취소")
  @DeleteMapping("/{articleId}")
  public ResponseEntity<ApiResult<Boolean>> deleteRecommand(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @PathVariable Long articleId
  ){
    recommandService.delete(userPrincipal.getUserId(), articleId);
    return ResponseEntity.ok(ApiResult.success(true));
  }
}
