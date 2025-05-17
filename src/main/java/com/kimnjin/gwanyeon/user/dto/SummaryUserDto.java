package com.kimnjin.gwanyeon.user.dto;

import com.kimnjin.gwanyeon.user.entity.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class SummaryUserDto {

  private String userLoginId;
  private String userName;
  private String userEmail;
  private UserRole userRole;
}
