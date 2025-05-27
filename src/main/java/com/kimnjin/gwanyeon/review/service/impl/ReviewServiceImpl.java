package com.kimnjin.gwanyeon.review.service.impl;

import com.kimnjin.gwanyeon.commons.exception.BadRequestException;
import com.kimnjin.gwanyeon.commons.exception.UnAuthorizedException;
import com.kimnjin.gwanyeon.review.dto.CreateReviewDto;
import com.kimnjin.gwanyeon.review.dto.ResponseReviewDto;
import com.kimnjin.gwanyeon.review.dto.UpdateReviewDto;
import com.kimnjin.gwanyeon.review.entity.Review;
import com.kimnjin.gwanyeon.review.repository.ReviewRepository;
import com.kimnjin.gwanyeon.review.service.ReviewService;
import com.kimnjin.gwanyeon.user.entity.User;
import com.kimnjin.gwanyeon.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;
  private final UserRepository userRepository;

  @Transactional
  @Override
  public ResponseReviewDto createReview(CreateReviewDto createReviewDto, Long articleid,
      Long userId) {
    Review review = Review.builder()
        .articleId(articleid)
        .userId(userId)
        .content(createReviewDto.getContent())
        .build();

    int result = reviewRepository.insertReview(review);
    if(result == 0) {
      throw new BadRequestException("댓글 등록 실패");
    }
    User writer = userRepository.findById(userId);

    return ResponseReviewDto.from(review, writer);
  }

  @Transactional
  @Override
  public ResponseReviewDto updateReview(UpdateReviewDto updateReviewDto, Long articleid,
      Long userId) {
    Review existingReview = reviewRepository.selectReviewById(updateReviewDto.getReviewId());
    if(existingReview == null) {
      throw new BadRequestException("잘못된 요청입니다.(댓글 접근)");
    }
    User updateUser = userRepository.findById(userId);
    if(updateUser == null) {
      throw new BadRequestException("잘못된 요청입니다.(유저 접근)");
    }
    existingReview.changeContent(updateReviewDto.getContent());
    int result = reviewRepository.updateReview(existingReview);
    if(result == 0) {
      throw new IllegalArgumentException("댓글 수정 실패");
    }
    return ResponseReviewDto.from(existingReview, updateUser);
  }

  @Transactional
  @Override
  public void deleteReview(Long reviewId, Long userId) {
    Review reivew = reviewRepository.selectReviewById(reviewId);
    if(reivew.getUserId() == userId) {
      throw new UnAuthorizedException("작성자만 삭제할 수 있습니다.");
    }
    int result = reviewRepository.deleteReview(reviewId);
    if(result == 0) {
      throw new IllegalArgumentException("댓글 삭제 실패");
    }
  }

  @Override
  public List<ResponseReviewDto> getReviewsByArticleId(Long articleId) {
    List<Review> reviews =reviewRepository.selectReviewsByArticleId(articleId);
    List<ResponseReviewDto> responseReviewDtos = new ArrayList<>();
    for(Review review : reviews) {
      responseReviewDtos.add(ResponseReviewDto.from(review, userRepository.findById(review.getUserId())));
    }
    return responseReviewDtos;
  }

  @Override
  public List<ResponseReviewDto> getReviewsByUserId(Long userId) {
    List<Review> reviews =reviewRepository.selectReviewsByUserId(userId);
    List<ResponseReviewDto> responseReviewDtos = new ArrayList<>();
    for(Review review : reviews) {
      responseReviewDtos.add(ResponseReviewDto.from(review, userRepository.findById(review.getUserId())));
    }
    return responseReviewDtos;
  }
}
