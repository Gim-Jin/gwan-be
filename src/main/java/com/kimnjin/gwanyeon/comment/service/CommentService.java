package com.kimnjin.gwanyeon.comment.service;

import com.kimnjin.gwanyeon.comment.dto.CommentResponseDto;
import com.kimnjin.gwanyeon.comment.dto.CreateCommentRequestDto;
import com.kimnjin.gwanyeon.comment.dto.ModifyCommentRequestDto;
import java.util.List;

public interface CommentService {

  CommentResponseDto save(CreateCommentRequestDto createCommentRequestDto, Long videoId);

  CommentResponseDto modify(ModifyCommentRequestDto modifyCommentRequestDto, Long commentId);

  void remove(Long id);

  List<CommentResponseDto> getAllCommentsByExerciseVideoId(Long exerciseVideoId);

  List<CommentResponseDto> getAllCommentsByUserId(Long userId);

  CommentResponseDto getCommentById(Long id);


}
