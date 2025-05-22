package com.kimnjin.gwanyeon.target.dto;

import com.kimnjin.gwanyeon.target.entity.Target;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TargetResponseDto {

  private Long targetId;

  private String name;

  public static TargetResponseDto from(Target target) {

    TargetResponseDto dto = new TargetResponseDto();

    dto.setTargetId(target.getTargetId());

    dto.setName(target.getName());
    
    return dto;
  }
}
