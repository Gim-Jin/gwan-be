package com.kimnjin.gwanyeon.user.dto;

import com.kimnjin.gwanyeon.user.entity.User;
import com.kimnjin.gwanyeon.user.entity.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class SummaryUserDto {

  private Long userId;
  private String loginId;
  private String name;
  private String email;
  private String password;
  private UserRole role;

  public static SummaryUserDto from(User user) {
    SummaryUserDto summaryUserDto = new SummaryUserDto();
    summaryUserDto.setUserId(user.getUserId());
    summaryUserDto.setLoginId(user.getLoginId());
    summaryUserDto.setName(user.getName());
    summaryUserDto.setEmail(user.getEmail());
    summaryUserDto.setPassword(user.getPassword());
    summaryUserDto.setRole(user.getRole());
    return summaryUserDto;

  }
}
