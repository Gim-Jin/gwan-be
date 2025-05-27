package com.kimnjin.gwanyeon.recommand.repository;

import com.kimnjin.gwanyeon.recommand.entity.Recommand;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecommandRepository {

  int insert(Recommand recommand);

  int delete(Recommand recommand);

  int countByUserId(Long userId);

  Recommand selectByUserIdAndArticleId(Long userId, Long articleId);
}
