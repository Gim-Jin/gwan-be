package com.kimnjin.gwanyeon.recommand.service.impl;

import com.kimnjin.gwanyeon.article.dto.ResponseArticleDto;
import com.kimnjin.gwanyeon.article.dto.SummaryArticleDto;
import com.kimnjin.gwanyeon.article.repository.ArticleRepository;
import com.kimnjin.gwanyeon.commons.exception.BadRequestException;
import com.kimnjin.gwanyeon.recommand.dto.CreateRecommandDto;
import com.kimnjin.gwanyeon.recommand.entity.Recommand;
import com.kimnjin.gwanyeon.recommand.repository.RecommandRepository;
import com.kimnjin.gwanyeon.recommand.service.RecommandService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecommandServiceImpl implements RecommandService {

  private final RecommandRepository recommandRepository;
  private final ArticleRepository articleRepository;

  @Transactional
  @Override
  public boolean save(CreateRecommandDto createRecommandDto) {
    Recommand recommand = createRecommandDto.toEntity();
    if(recommandRepository.insert(recommand) == 0) {
      throw new BadRequestException("잘못된 요청입니다.");
    }
    return true;
  }

  @Transactional
  @Override
  public boolean delete(Long userId, Long recommandId) {
    Recommand recommand = Recommand.builder().userId(userId).recommandId(recommandId).build();
    if(recommandRepository.delete(recommand) == 0) {
      throw new BadRequestException("잘못된 요청입니다.");
    }
    return true;
  }

  @Override
  public boolean isRecommand(Long articleId, Long userId) {
    Recommand recommand = recommandRepository.selectByUserIdAndArticleId(userId, articleId);
    return recommand != null;
  }

  @Override
  public List<SummaryArticleDto> getRecommandArticle(Long userId) {
    List<SummaryArticleDto> articles = articleRepository.selectRecommendArticleByUserId(userId);
    return articles;
  }
}
