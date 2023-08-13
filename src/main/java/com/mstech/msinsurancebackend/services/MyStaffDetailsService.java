package com.mstech.msinsurancebackend.services;

import com.mstech.msinsurancebackend.models.MyStaffDetails;
import com.mstech.msinsurancebackend.models.Staff;
import com.mstech.msinsurancebackend.repositories.StaffRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
    Optional<Staff> staff = staffRepository.findByUsername(username);
    staff.orElseThrow(() ->
      new UsernameNotFoundException("Not found: " + username)
    );
    return staff.map(MyStaffDetails::new).get();
  }
}
