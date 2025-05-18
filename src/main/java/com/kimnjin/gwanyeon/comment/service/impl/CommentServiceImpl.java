package com.kimnjin.gwanyeon.comment.service.impl;

import com.kimnjin.gwanyeon.comment.dto.CommentResponseDto;
import com.kimnjin.gwanyeon.comment.dto.CreateCommentRequestDto;
import com.kimnjin.gwanyeon.comment.dto.ModifyCommentRequestDto;
import com.kimnjin.gwanyeon.comment.entity.Comment;
import com.kimnjin.gwanyeon.comment.repository.CommentRepository;
import com.kimnjin.gwanyeon.comment.service.CommentService;
import com.kimnjin.gwanyeon.commons.exception.BadRequestException;
import com.kimnjin.gwanyeon.commons.exception.ResourceNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  private final String BAD_REQUEST = "잘못된 요청입니다.";

  private final String NOT_FOUND = "댓글을 찾을 수 없습니다.";

  @Override
  public CommentResponseDto save(CreateCommentRequestDto createCommentRequestDto, Long videoId) {

    Comment comment = createCommentRequestDto.toEntity();

    comment.setExerciseVideoId(videoId);

    if (commentRepository.insert(comment) == 0) {

      throw new BadRequestException(BAD_REQUEST);
    }

    return CommentResponseDto.from(comment);

  }

  @Override
  public CommentResponseDto modify(ModifyCommentRequestDto modifyCommentRequestDto,
      Long commentId) {

    Comment existingComment = commentRepository.selectById(commentId);

    if (existingComment == null) {

      throw new ResourceNotFoundException(commentId + NOT_FOUND);
    }

    existingComment.setContent(modifyCommentRequestDto.getContent());

    int result = commentRepository.update(existingComment);

    if (result == 0) {

      throw new BadRequestException(BAD_REQUEST);
    }

    return CommentResponseDto.from(existingComment);

  }

  @Override
  public void remove(Long id) {

    int result = commentRepository.delete(id);

    if (result == 0) {

      throw new ResourceNotFoundException(id + NOT_FOUND);
    }

  }

  @Override
  public List<CommentResponseDto> getAllCommentsByExerciseVideoId(Long exerciseVideoId) {

    List<Comment> comments = commentRepository.selectAllByExerciseVideoId(exerciseVideoId);

    if (comments.isEmpty()) {

      return Collections.emptyList();
    }

    return comments.stream().map(CommentResponseDto::from).collect(Collectors.toList());

  }

  @Override
  public List<CommentResponseDto> getAllCommentsByUserId(Long userId) {

    List<Comment> comments = commentRepository.selectAllByUserId(userId);

    if (comments.isEmpty()) {

      return Collections.emptyList();
    }

    return comments.stream().map(CommentResponseDto::from).collect(Collectors.toList());

  }

  @Override
  public CommentResponseDto getCommentById(Long id) {

    Comment comment = commentRepository.selectById(id);

    if (comment == null) {

      throw new ResourceNotFoundException(id + NOT_FOUND);
    }

    return CommentResponseDto.from(comment);

  }
}
