package com.mstech.msinsurancebackend.security;

import com.mstech.msinsurancebackend.security.jwt.JwtAuthenticationFilter;
import com.mstech.msinsurancebackend.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final UnauthorizedHandler unauthorizedHandler;
  private final CustomUserDetailsService myStaffDetailsService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .addFilterBefore(
        jwtAuthenticationFilter,
        UsernamePasswordAuthenticationFilter.class
      )
      .cors(AbstractHttpConfigurer::disable)
      .csrf(AbstractHttpConfigurer::disable)
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .formLogin(AbstractHttpConfigurer::disable)
      .exceptionHandling(exception ->
        exception.authenticationEntryPoint(unauthorizedHandler)
      )
      .securityMatcher("/**")
      .authorizeHttpRequests(auth ->
        auth
          .requestMatchers("/", "/home", "/auth/login", "auth/register")
          .permitAll()
          .requestMatchers("/staff")
          .hasAnyRole("ADMIN", "STAFF")
          .requestMatchers("/api/v1/**", "/admin")
          .hasRole("ADMIN")
          .anyRequest()
          .authenticated()
      );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http)
    throws Exception {
    return http
      .getSharedObject(AuthenticationManagerBuilder.class)
      .userDetailsService(myStaffDetailsService)
      .passwordEncoder(passwordEncoder())
      .and()
      .build();
  }
}
