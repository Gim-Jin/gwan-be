package com.kimnjin.gwanyeon.rehabprogram.entity;


import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class RehabProgram {

  private Long rehabProgramId;

  private Long userId;

  private Part part;

  private String question;

  private String prescription;

  private Boolean isDone;

  private LocalDateTime createdAt;

  private LocalDateTime refreshAt;

}
