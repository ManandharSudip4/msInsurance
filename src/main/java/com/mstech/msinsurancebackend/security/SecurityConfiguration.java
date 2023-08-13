package com.mstech.msinsurancebackend.security;

import com.mstech.msinsurancebackend.services.MyStaffDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  //   public final MyStaffDetailsService myStaffDetailsService;
  private final MyStaffDetailsService staffDetailsService;

  public SecurityConfiguration(MyStaffDetailsService myStaffDetailsService) {
    this.staffDetailsService = myStaffDetailsService;
  }

  @SuppressWarnings("deprecation")
  @Bean
  public AuthenticationManager authenticationManager(
    HttpSecurity http,
    NoOpPasswordEncoder noOpPasswordEncoder
  ) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
      AuthenticationManagerBuilder.class
    );
    authenticationManagerBuilder
      .userDetailsService(staffDetailsService)
      .passwordEncoder(noOpPasswordEncoder);
    return authenticationManagerBuilder.build();
  }


  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .authorizeHttpRequests(auth ->
        auth
          .requestMatchers("/home")
          .hasRole("ADMIN")
          .anyRequest()
          .authenticated()
      )
      .formLogin(Customizer.withDefaults());

    return http.build();
  }
}
