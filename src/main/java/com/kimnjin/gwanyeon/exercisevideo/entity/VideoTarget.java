package com.kimnjin.gwanyeon.exercisevideo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * VideoTarget을 넣기 위한 dto
 */
@Getter
@Setter
@AllArgsConstructor
public class VideoTarget {
  Long exerciseVideoId;
  Long targetId;
}
