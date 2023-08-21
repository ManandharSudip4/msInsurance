package com.mstech.msinsurancebackend.services;

import com.mstech.msinsurancebackend.repositories.StaffRepository;
import com.mstech.msinsurancebackend.security.UserPrincipal;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyStaffDetailsService implements UserDetailsService {

  @Autowired
  StaffRepository staffRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    var staff = staffRepository.findByUsername(username).orElseThrow();
    // System.out.println(staff.map(MyStaffDetails::new).get().getAuthorities());
    // staff.orElseThrow(() ->
    //   new UsernameNotFoundException("Not found: " + username)
    // );
    // return staff.map(MyStaffDetails::new).get();
    return UserPrincipal
      .builder()
      .userId(staff.getId())
      .email(staff.getUsername())
      .password(staff.getPassword())
      .authorities(List.of(new SimpleGrantedAuthority(staff.getRoles())))
      .build();
  }
}
