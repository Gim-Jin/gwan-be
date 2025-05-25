package com.kimnjin.gwanyeon.commons.security;

import com.kimnjin.gwanyeon.user.entity.User;
import com.kimnjin.gwanyeon.user.entity.UserRole;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

  private final Long userId;
  private final String userLoginId;
  private final String password;
  private final UserRole role;

  public UserPrincipal(User user) {
    this.userId = user.getUserId();
    this.userLoginId = user.getLoginId();
    this.password = user.getPassword();
    this.role = user.getRole();

    System.out.println("유저 권한: "+user.getRole());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(() -> "ROLE_" + role);
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userLoginId;
  }

  public Long getUserId() {
    return userId;
  }

  public String getRole() {
    return role.toString();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
