package com.kimnjin.gwanyeon.exercisevideo.repository;

import com.kimnjin.gwanyeon.exercisevideo.entity.ExerciseVideo;
import com.kimnjin.gwanyeon.exercisevideo.entity.ExerciseVideoWithTarget;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExerciseVideoRepository {

  int insert(ExerciseVideo video);

  int update(ExerciseVideo video);

  int deleteById(Long id);

  ExerciseVideo selectById(Long id);

  List<ExerciseVideo> selectAll();

  ExerciseVideoWithTarget selectByIdWithTarget(Long id);

  List<ExerciseVideoWithTarget> selectAllWithTargets();

  List<ExerciseVideoWithTarget> selectNineVideosWithLikeCount();

  List<ExerciseVideoWithTarget> searchByKeyword(String keyword);

  List<ExerciseVideoWithTarget> searchByTarget(String target);
}
