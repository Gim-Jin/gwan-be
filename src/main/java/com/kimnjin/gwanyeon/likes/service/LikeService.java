package com.kimnjin.gwanyeon.likes.service;

import com.kimnjin.gwanyeon.likes.dto.CreateLikeDto;

public interface LikeService {

  boolean save(CreateLikeDto dto);

  boolean remove(Long userId, Long exerciseVideoId);
}
