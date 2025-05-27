package com.kimnjin.gwanyeon.commons.config;


import com.kimnjin.gwanyeon.auth.jwt.TokenProvider;
import com.kimnjin.gwanyeon.commons.security.CustomUserDetailsService;
import com.kimnjin.gwanyeon.commons.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

  private final TokenProvider tokenProvider;
  private final CustomUserDetailsService customUserDetailsService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults())
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            // 인증 관련 엔드포인트
            .requestMatchers(
                "/api/auth/login",
                "/api/auth/signup",
                "/api/auth/reissue",
                "/api/auth/logout"
            ).permitAll()

            // 공개 API
            .requestMatchers(HttpMethod.GET, "/api/exercise-videos/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/targets").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/articles").permitAll()

            // Swagger UI
            .requestMatchers(
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/v3/api-docs/**",
                "/v3/api-docs",
                "/webjars/**"
            ).permitAll()

            // 나머지 요청은 인증 필요
            .anyRequest().authenticated()
        )
        .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .build();


    // 개발용
//    return http
//        .csrf(AbstractHttpConfigurer::disable)
//        .cors(Customizer.withDefaults())
//        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//        .authorizeHttpRequests(auth -> auth
//            .requestMatchers("/**").permitAll().anyRequest().authenticated()
//        )
//        .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//        .build();
  }

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter(tokenProvider, customUserDetailsService);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
