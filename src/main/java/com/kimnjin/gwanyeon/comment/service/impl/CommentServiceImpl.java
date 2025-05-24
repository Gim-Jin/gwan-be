package com.kimnjin.gwanyeon.comment.service.impl;

import com.kimnjin.gwanyeon.comment.dto.CommentResponseDto;
import com.kimnjin.gwanyeon.comment.dto.CreateCommentRequestDto;
import com.kimnjin.gwanyeon.comment.dto.ModifyCommentRequestDto;
import com.kimnjin.gwanyeon.comment.entity.Comment;
import com.kimnjin.gwanyeon.comment.entity.CommentWithNickname;
import com.kimnjin.gwanyeon.comment.entity.CommentWithVideoTitleAndUrl;
import com.kimnjin.gwanyeon.comment.repository.CommentRepository;
import com.kimnjin.gwanyeon.comment.service.CommentService;
import com.kimnjin.gwanyeon.commons.exception.BadRequestException;
import com.kimnjin.gwanyeon.commons.exception.ResourceNotFoundException;
import com.kimnjin.gwanyeon.commons.exception.UnAuthorizedException;
import com.kimnjin.gwanyeon.commons.security.UserPrincipal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  private final String BAD_REQUEST = "잘못된 요청입니다.";

  private final String NOT_FOUND = "댓글을 찾을 수 없습니다.";

  @Override
  @Transactional
  public CommentResponseDto save(CreateCommentRequestDto createCommentRequestDto, Long videoId,
      Long userId) {

    createCommentRequestDto.setUserId(userId);

    Comment comment = createCommentRequestDto.toEntity();

    comment.setExerciseVideoId(videoId);

    if (commentRepository.insert(comment) == 0) {

      throw new BadRequestException(BAD_REQUEST);
    }

    return CommentResponseDto.from(comment);

  }

  @Override
  @Transactional
  public CommentResponseDto modify(ModifyCommentRequestDto modifyCommentRequestDto) {

    Comment existingComment = commentRepository.selectById(modifyCommentRequestDto.getCommentId());

    if (existingComment == null) {

      throw new ResourceNotFoundException(modifyCommentRequestDto.getCommentId() + NOT_FOUND);
    }

    if (!modifyCommentRequestDto.getUserId().equals(existingComment.getUserId())) {
      throw new UnAuthorizedException("권한이 없습니다.");
    }

    existingComment.setContent(modifyCommentRequestDto.getContent());

    int result = commentRepository.update(existingComment);

    if (result == 0) {

      throw new BadRequestException(BAD_REQUEST);
    }

    return CommentResponseDto.from(existingComment);

  }

  @Override
  @Transactional
  public void remove(Long id, UserPrincipal user) {

    // 영자는 다 지우게 해 줘야지.
    if (user.getRole().equals("ADMIN")) {

      int result = commentRepository.delete(id);

      if (result == 0) {
        throw new ResourceNotFoundException(id + NOT_FOUND);
      }
    } else {

      // 만약 댓글을 쓴 사람이랑 지우려고 하는 댓글이 같으면?
      if (commentRepository.selectById(id).getUserId().equals(user.getUserId())) {
        int result = commentRepository.delete(id);
        if (result == 0) {
          throw new ResourceNotFoundException(id + NOT_FOUND);
        }
        //아니면 막아.
      } else {
        throw new UnAuthorizedException("권한이 없습니다ㅏ.");
      }
    }


  }

  @Override
  public List<CommentResponseDto> getAllCommentsByExerciseVideoId(Long exerciseVideoId) {

    List<CommentWithNickname> comments = commentRepository.selectAllByExerciseVideoId(
        exerciseVideoId);

    if (comments.isEmpty()) {

      return Collections.emptyList();
    }

    return comments.stream().map(CommentResponseDto::fromWithNickname).collect(Collectors.toList());

  }

  @Override
  public List<CommentResponseDto> getAllCommentsByUserId(Long userId) {

    List<CommentWithVideoTitleAndUrl> comments = commentRepository.selectAllByUserId(userId);

    if (comments.isEmpty()) {

      return Collections.emptyList();
    }

    return comments.stream().map(CommentResponseDto::fromWithVideoTitle)
        .collect(Collectors.toList());

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
