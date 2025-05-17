package com.kimnjin.gwanyeon.user.entity;


import com.kimnjin.gwanyeon.commons.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Setter
public class User extends BaseEntity {

  private Long userId;
  private String userLoginId;
  private String userPassword;
  private String userEmail;
  private String userName;
  private String userNickname;
  private UserRole userRole;


  public void changePassword(String password) {
    this.userPassword = password;
  }

  public void changeUserEmail(String email) {
    this.userEmail = email;
  }

  public void changeUserNickname(String nickname) {
    this.userNickname = nickname;
  }

  public void changeUserRole(UserRole role) {
    this.userRole = role;
  }

}
