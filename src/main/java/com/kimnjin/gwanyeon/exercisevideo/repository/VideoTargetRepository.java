package com.kimnjin.gwanyeon.exercisevideo.repository;

import com.kimnjin.gwanyeon.exercisevideo.entity.VideoTarget;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoTargetRepository {

  int insert(VideoTarget target);
  int deleteById(Long id);
}
