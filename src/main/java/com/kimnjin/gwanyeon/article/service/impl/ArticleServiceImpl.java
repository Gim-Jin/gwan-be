package com.kimnjin.gwanyeon.article.service.impl;

import com.kimnjin.gwanyeon.article.dto.CreateArticleDto;
import com.kimnjin.gwanyeon.article.dto.ResponseArticleDto;
import com.kimnjin.gwanyeon.article.dto.SummaryArticleDto;
import com.kimnjin.gwanyeon.article.dto.UpdateArticleDto;
import com.kimnjin.gwanyeon.article.entity.Article;
import com.kimnjin.gwanyeon.article.repository.ArticleRepository;
import com.kimnjin.gwanyeon.article.service.ArticleService;
import com.kimnjin.gwanyeon.commons.exception.BadRequestException;
import com.kimnjin.gwanyeon.commons.exception.UnAuthorizedException;
import com.kimnjin.gwanyeon.user.entity.User;
import com.kimnjin.gwanyeon.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

  private final ArticleRepository articleRepository;
  private final UserRepository userRepository;

  @Transactional
  @Override
  public ResponseArticleDto saveArticle(CreateArticleDto createArticleDto, Long userId) {

    User user = userRepository.findById(userId);

    Article article = Article.builder()
        .userId(userId)
        .title(createArticleDto.getTitle())
        .content(createArticleDto.getContent())
        .build();
    int result = articleRepository.insert(article);

    if (result == 0) {
      throw new BadRequestException("게시글 등록 실패");
    }
    return ResponseArticleDto.from(article, user.getUserId(), user.getNickname());
  }

  @Transactional
  @Override
  public ResponseArticleDto updateArticle(UpdateArticleDto updateArticleDto, Long userId) {

    Article existingArticle = articleRepository.selectArticle(updateArticleDto.getArticleId());
    if (existingArticle.getUserId() != userId) {
      throw new BadRequestException("작성자만 수정할 수 있습니다.");
    }
    User user = userRepository.findById(userId);
    existingArticle.changeTitle(updateArticleDto.getTitle());
    existingArticle.changeContent(updateArticleDto.getContent());
    int result = articleRepository.update(existingArticle);
    if (result == 0) {
      throw new BadRequestException("게시글 수정 실패");
    }
    return ResponseArticleDto.from(existingArticle, user.getUserId(), user.getNickname());
  }

  @Transactional
  @Override
  public void removeArticle(Long articleId, Long userId) {
    Article article = articleRepository.selectArticle(articleId);
    if (article.getUserId() != userId) {
      throw new UnAuthorizedException("작성자만 삭제할 수 있습니다.");
    }
    int result = articleRepository.delete(articleId);
    if (result == 0) {
      throw new BadRequestException("게시글 삭제 실패");
    }
  }

  @Override
  public List<SummaryArticleDto> getAllArticles() {
    return articleRepository.selectArticles();
  }

  @Override
  public ResponseArticleDto getArticleById(Long articleId) {
    Article article = articleRepository.selectArticle(articleId);
    if (article == null) {
      throw new BadRequestException("글이 없습니다.");
    }
    User writer = userRepository.findById(article.getUserId());
    return ResponseArticleDto.from(article, writer.getUserId(), writer.getNickname());
  }

  @Override
  public List<SummaryArticleDto> getArticlesByUserId(Long userId) {
    return articleRepository.selectArticlesByUserId(userId);
  }
}
