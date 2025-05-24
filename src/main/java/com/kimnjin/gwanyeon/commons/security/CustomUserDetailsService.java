package com.kimnjin.gwanyeon.commons.security;


import com.kimnjin.gwanyeon.user.entity.User;
import com.kimnjin.gwanyeon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userSeq) throws UsernameNotFoundException {
    Long userId = Long.parseLong(userSeq);
    User user=userRepository.findById(userId);
    if(user==null) {
      throw new UsernameNotFoundException(userSeq+"인 사용자가 없습니다");
    }
    return new UserPrincipal(user);
  }
}
