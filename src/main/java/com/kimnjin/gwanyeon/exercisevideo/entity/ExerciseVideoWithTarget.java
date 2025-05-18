package com.kimnjin.gwanyeon.exercisevideo.entity;


import com.kimnjin.gwanyeon.commons.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * Target하고 같이 가져오기 위한 Entity
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Setter
@ToString
public class ExerciseVideoWithTarget extends ExerciseVideo {

  List<String> targets;

}
