package com.kimnjin.gwanyeon.article.repository;

import com.kimnjin.gwanyeon.article.dto.SummaryArticleDto;
import com.kimnjin.gwanyeon.article.entity.Article;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleRepository {

  public int insert(Article article);
  public int update(Article article);
  public int delete(Long articleId);
  public Article selectArticle(Long articleId);
  public List<SummaryArticleDto> searchByTitle(String Title);
  public List<SummaryArticleDto> selectArticlesByUserId(Long userId);
  public List<SummaryArticleDto> selectArticles();
  public List<SummaryArticleDto> selectRecommendArticleByUserId(Long userId);
}
