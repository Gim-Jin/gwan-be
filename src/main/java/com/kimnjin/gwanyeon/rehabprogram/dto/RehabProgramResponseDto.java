package com.kimnjin.gwanyeon.rehabprogram.dto;

import com.kimnjin.gwanyeon.rehabprogram.entity.Part;
import com.kimnjin.gwanyeon.rehabprogram.entity.RehabProgram;
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

  private LocalDateTime refreshAt;


  public static RehabProgramResponseDto from(RehabProgram rehabProgram) {

    RehabProgramResponseDto dto = new RehabProgramResponseDto();

    dto.setRoutineId(rehabProgram.getRehabProgramId());

    dto.setUserId(rehabProgram.getUserId());

    dto.setPart(rehabProgram.getPart());

    dto.setQuestion(rehabProgram.getQuestion());

    dto.setPrescription(rehabProgram.getPrescription());

    dto.setIsDone(rehabProgram.getIsDone());

    dto.setCreatedAt(rehabProgram.getCreatedAt());

    dto.setRefreshAt(rehabProgram.getRefreshAt());

    return dto;

  }

}
