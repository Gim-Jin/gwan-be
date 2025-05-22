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
public class CreateTargetRequestDto {

  private String name;

  public Target toEntity() {
    return Target.builder()
        .name(this.name)
        .build();
  }

}
