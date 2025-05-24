package com.kimnjin.gwanyeon.comment.repository;

import com.kimnjin.gwanyeon.comment.entity.Comment;
import com.kimnjin.gwanyeon.comment.entity.CommentWithNickname;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentRepository {

  int insert(Comment comment);

  int update(Comment comment);

  int delete(Long id);

  Comment selectById(Long id);

  List<CommentWithNickname> selectAllByExerciseVideoId(Long exerciseVideoId);

  List<Comment> selectAllByUserId(Long userId);


}
