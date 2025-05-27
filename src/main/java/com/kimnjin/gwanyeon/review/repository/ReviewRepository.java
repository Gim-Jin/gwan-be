package com.kimnjin.gwanyeon.review.repository;

import com.kimnjin.gwanyeon.review.dto.ResponseReviewDto;
import com.kimnjin.gwanyeon.review.dto.UpdateReviewDto;
import com.kimnjin.gwanyeon.review.entity.Review;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewRepository {

  int insertReview(Review review);

  int deleteReview(Long reviewId);

  int updateReview(Review review);

  List<Review> selectReviewsByArticleId(Long articleId);

  List<ResponseReviewDto> selectReviewsByUserId(Long userId);

  Review selectReviewById(Long reviewId);

  List<ResponseReviewDto> selectReviewsForArticle(Long articleId);

}
