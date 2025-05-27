package com.kimnjin.gwanyeon.recommand.service;

import com.kimnjin.gwanyeon.article.dto.SummaryArticleDto;
import com.kimnjin.gwanyeon.recommand.dto.CreateRecommandDto;
import java.util.List;

public interface RecommandService {

  boolean save(CreateRecommandDto createRecommandDto);

  boolean delete(Long userId,Long recommandId);

  boolean isRecommand(Long articleId, Long userId);

  List<SummaryArticleDto> getRecommandArticle(Long userId);
}
