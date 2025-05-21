package com.kimnjin.gwanyeon.rehabprogram.dto;

import com.kimnjin.gwanyeon.rehabprogram.entity.Part;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class RehabProgramResponseDto {

  private Long routineId;

  private Long userId;

  private Part part;

  private String question;

  private String prescription;

  private Boolean isDone;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

}
