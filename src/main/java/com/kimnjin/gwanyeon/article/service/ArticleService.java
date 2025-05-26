package com.kimnjin.gwanyeon.article.service;


import com.kimnjin.gwanyeon.article.dto.CreateArticleDto;
import com.kimnjin.gwanyeon.article.dto.ResponseArticleDto;
import com.kimnjin.gwanyeon.article.dto.SummaryArticleDto;
import com.kimnjin.gwanyeon.article.dto.UpdateArticleDto;
import java.util.List;

public interface ArticleService {
  public ResponseArticleDto saveArticle(CreateArticleDto createArticleDto, Long userId);
  public ResponseArticleDto updateArticle(UpdateArticleDto updateArticleDto, Long userId);
  public void removeArticle(Long articleId,Long userId);
  public List<SummaryArticleDto> getAllArticles();
  public ResponseArticleDto getArticleById(Long articleId);
  public List<SummaryArticleDto> getArticlesByUserId(Long userId);
}
