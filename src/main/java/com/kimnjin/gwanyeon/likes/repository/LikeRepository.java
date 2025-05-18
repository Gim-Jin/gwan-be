package com.kimnjin.gwanyeon.likes.repository;


import com.kimnjin.gwanyeon.likes.entity.Like;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeRepository {

  int insert(Like like);

  int delete(Like like);

}
