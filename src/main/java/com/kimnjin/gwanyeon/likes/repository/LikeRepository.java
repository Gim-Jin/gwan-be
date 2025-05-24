package com.kimnjin.gwanyeon.likes.repository;


import com.kimnjin.gwanyeon.likes.entity.Like;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeRepository {

  int insert(Like like);

  int delete(Like like);

  int countByUserId(Long userId);

  Like selectByUserIdAndVideoId(@Param("userId") Long userId, @Param("videoId") Long videoId);

}
