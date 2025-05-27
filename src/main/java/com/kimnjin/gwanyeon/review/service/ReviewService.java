package com.kimnjin.gwanyeon.review.service;

import com.kimnjin.gwanyeon.review.dto.CreateReviewDto;
import com.kimnjin.gwanyeon.review.dto.ResponseReviewDto;
import com.kimnjin.gwanyeon.review.dto.UpdateReviewDto;
import com.kimnjin.gwanyeon.review.repository.ReviewRepository;
import java.util.List;

public interface ReviewService {

  public ResponseReviewDto createReview(CreateReviewDto createReviewDto,Long articleid,Long userId);

  public ResponseReviewDto updateReview(UpdateReviewDto updateReviewDto,Long articleid,Long userId);

  public void deleteReview(Long reviewId,Long userId);

  public List<ResponseReviewDto> getReviewsByArticleId(Long articleid);

  public List<ResponseReviewDto> getReviewsByUserId(Long userId);

}
