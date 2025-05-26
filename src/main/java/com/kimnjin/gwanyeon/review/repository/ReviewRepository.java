package com.kimnjin.gwanyeon.review.repository;

import com.kimnjin.gwanyeon.review.dto.UpdateReviewDto;
import com.kimnjin.gwanyeon.review.entity.Review;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewRepository {

  int insertReview(Review review);

  int deleteReview(Long reivewId, Long userId);

  int updateReview(UpdateReviewDto updateReviewDto);

  List<Review> selectReviews(Long articleId);

}
