package com.kimnjin.gwanyeon.exercisevideo.repository;

import com.kimnjin.gwanyeon.exercisevideo.entity.ExerciseVideo;
import com.kimnjin.gwanyeon.exercisevideo.entity.ExerciseVideoWithTarget;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExerciseVideoRepository {

  int insert(ExerciseVideo video);

  int update(ExerciseVideo video);

  int deleteById(Long id);

  ExerciseVideo selectById(Long id);

  List<ExerciseVideo> selectAll();

  List<ExerciseVideo> selectLikesVideoByUserId(Long userId);

  ExerciseVideoWithTarget selectByIdWithTarget(Long id);

  List<ExerciseVideoWithTarget> selectAllWithTargets();

  List<ExerciseVideoWithTarget> selectNineVideosWithLikeCount();

  List<ExerciseVideoWithTarget> searchWithConditions(
      @Param("keyword") String keyword,
      @Param("target") String target,
      @Param("sort") String sort
  );
}
