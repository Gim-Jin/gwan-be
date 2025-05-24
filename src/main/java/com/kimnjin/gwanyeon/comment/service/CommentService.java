package com.kimnjin.gwanyeon.comment.service;

import com.kimnjin.gwanyeon.comment.dto.CommentResponseDto;
import com.kimnjin.gwanyeon.comment.dto.CreateCommentRequestDto;
import com.kimnjin.gwanyeon.comment.dto.ModifyCommentRequestDto;
import com.kimnjin.gwanyeon.commons.security.UserPrincipal;
import java.util.List;

public interface CommentService {

  CommentResponseDto save(CreateCommentRequestDto createCommentRequestDto, Long videoId,
      Long userId);

  CommentResponseDto modify(ModifyCommentRequestDto modifyCommentRequestDto);

  void remove(Long id, UserPrincipal user);

  List<CommentResponseDto> getAllCommentsByExerciseVideoId(Long exerciseVideoId);

  List<CommentResponseDto> getAllCommentsByUserId(Long userId);

  CommentResponseDto getCommentById(Long id);


}
