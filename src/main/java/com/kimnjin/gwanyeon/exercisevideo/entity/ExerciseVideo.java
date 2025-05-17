package com.kimnjin.gwanyeon.exercisevideo.entity;


import com.kimnjin.gwanyeon.commons.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Setter
public class ExerciseVideo extends BaseEntity {

  Long exerciseVideoId;

  Long userId;

  String title;

  String url;

  String youtubeId;

  String description;

}
