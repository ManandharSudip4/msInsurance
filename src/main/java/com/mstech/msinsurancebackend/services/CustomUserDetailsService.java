package com.mstech.msinsurancebackend.services;

import com.mstech.msinsurancebackend.repositories.UserRepository;
import com.mstech.msinsurancebackend.security.UserPrincipal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepository staffRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    var staff = staffRepository.findByUsername(username).orElseThrow();
    return UserPrincipal
      .builder()
      .userId(staff.getId())
      .email(staff.getUsername())
      .password(staff.getPassword())
      .authorities(List.of(new SimpleGrantedAuthority(staff.getRoles())))
      .build();
  }
}
