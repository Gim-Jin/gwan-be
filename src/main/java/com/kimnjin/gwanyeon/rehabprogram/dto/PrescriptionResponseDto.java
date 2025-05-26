package com.kimnjin.gwanyeon.rehabprogram.dto;

import com.kimnjin.gwanyeon.rehabprogram.entity.Prescription;
import com.kimnjin.gwanyeon.target.entity.Target;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionResponseDto {

  private Long rehabProgramId;

  private Long userId;

  private String prescription;

  private Boolean isdone;

  private Target target;

  private LocalDateTime createdAt;

  private LocalDateTime refreshAt;

  public static PrescriptionResponseDto from(Prescription prescription) {

    PrescriptionResponseDto prescriptionResponseDto = new PrescriptionResponseDto();
    prescriptionResponseDto.setRehabProgramId(prescription.getRehabProgramId());
    prescriptionResponseDto.setUserId(prescription.getUserId());
    prescriptionResponseDto.setPrescription(prescription.getPrescription());
    prescriptionResponseDto.setIsdone(prescription.getIsdone());
    prescriptionResponseDto.setTarget(prescription.getTarget());
    prescriptionResponseDto.setCreatedAt(prescription.getCreatedAt());
    prescriptionResponseDto.setRefreshAt(prescription.getRefreshAt());

    return prescriptionResponseDto;

  }

}
