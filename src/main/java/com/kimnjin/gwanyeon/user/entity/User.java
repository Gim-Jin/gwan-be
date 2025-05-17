package com.kimnjin.gwanyeon.user.entity;


import com.kimnjin.gwanyeon.commons.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Setter
@ToString
public class User extends BaseEntity {

  private Long userId;
  private String loginId;
  private String password;
  private String email;
  private String name;
  private String nickname;
  private UserRole role;


  public void changePassword(String password) {
    this.password = password;
  }

  public void changeUserEmail(String email) {
    this.email = email;
  }

  public void changeUserNickname(String nickname) {
    this.nickname = nickname;
  }

  public void changeUserRole(UserRole role) {
    this.role = role;
  }

}
