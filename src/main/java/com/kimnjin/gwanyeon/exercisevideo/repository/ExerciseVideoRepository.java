package com.kimnjin.gwanyeon.exercisevideo.repository;

import com.kimnjin.gwanyeon.exercisevideo.entity.ExerciseVideo;
import java.util.List;

public interface ExerciseVideoRepository {

  int insert(ExerciseVideo video);
  int update(ExerciseVideo video);
  int deleteById(Long id);
  ExerciseVideo findById(Long id);
  List<ExerciseVideo> selectAll();
  

}
