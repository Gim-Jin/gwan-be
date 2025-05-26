package com.kimnjin.gwanyeon.article.controller;

import com.kimnjin.gwanyeon.article.dto.CreateArticleDto;
import com.kimnjin.gwanyeon.article.dto.ResponseArticleDto;
import com.kimnjin.gwanyeon.article.dto.SummaryArticleDto;
import com.kimnjin.gwanyeon.article.dto.UpdateArticleDto;
import com.kimnjin.gwanyeon.article.service.ArticleService;
import com.kimnjin.gwanyeon.commons.dto.ApiResult;
import com.kimnjin.gwanyeon.commons.exception.BadRequestException;
import com.kimnjin.gwanyeon.commons.security.UserPrincipal;
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
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {

  private final ArticleService articleService;

  @Operation(summary = "게시글 작성", description = "회원인 유저는 글을 쓸 수 있음")
  @PostMapping("/create")
  public ResponseEntity<ApiResult<ResponseArticleDto>> createArticle(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestBody CreateArticleDto createArticleDto
  ) {
    ResponseArticleDto responseArticleDto = articleService.saveArticle(createArticleDto,
        userPrincipal.getUserId());
    return ResponseEntity.ok(ApiResult.success(responseArticleDto));
  }

  @Operation(summary = "게시글 수정", description = "본인 글을 수정할 수 있음")
  @PutMapping("/{articleId}")
  public ResponseEntity<ApiResult<ResponseArticleDto>> updateArticle(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @PathVariable Long articleId,
      @RequestBody UpdateArticleDto updateArticleDto
  ) {
    if (articleId != updateArticleDto.getArticleId()) {
      throw new BadRequestException("수정하고 싶은 글이 아닙니다.");
    }
    ResponseArticleDto responseArticleDto = articleService.updateArticle(updateArticleDto,
        userPrincipal.getUserId());
    return ResponseEntity.ok(ApiResult.success(responseArticleDto));
  }

  @Operation(summary = "게시글 삭제", description = "본인 글을 삭제할 수 있음")
  @DeleteMapping("/{articleId}")
  public ResponseEntity<ApiResult<String>> removeArticle(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @PathVariable Long articleId
  ) {
    articleService.removeArticle(articleId, userPrincipal.getUserId());
    return ResponseEntity.ok(ApiResult.success("삭제 성공"));
  }

  @Operation(summary = "게시글 자세히 보기", description = "게시글 보기")
  @GetMapping("/{articleId}")
  public ResponseEntity<ApiResult<ResponseArticleDto>> getArticle(@PathVariable Long articleId) {
    ResponseArticleDto responseArticleDto = articleService.getArticleById(articleId);
    return ResponseEntity.ok(ApiResult.success(responseArticleDto));
  }

  @Operation(summary = "게시글 전체 보기", description = "게시글 리스트를 호출")
  @GetMapping
  public ResponseEntity<ApiResult<List<SummaryArticleDto>>> getAllArticles() {
    List<SummaryArticleDto> articles = articleService.getAllArticles();
    return ResponseEntity.ok(ApiResult.success(articles, 200, "게시글 호출 성공"));
  }

}
