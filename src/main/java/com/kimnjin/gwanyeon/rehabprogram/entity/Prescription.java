package com.kimnjin.gwanyeon.rehabprogram.entity;


import com.kimnjin.gwanyeon.target.entity.Target;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Prescription {

  private Long rehabProgramId;

  private Long userId;

  private String prescription;

  private Target target;

  private Boolean isdone;

  private LocalDateTime createdAt;

  private LocalDateTime refreshAt;

}
