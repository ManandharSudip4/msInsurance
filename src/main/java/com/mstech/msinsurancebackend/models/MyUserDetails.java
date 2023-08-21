package com.mstech.msinsurancebackend.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mstech.msinsurancebackend.entities.UserEntity;

public class MyUserDetails implements UserDetails {

  private String username;
  private String password;
  private boolean active;
  private List<GrantedAuthority> authorities;

  public MyUserDetails(UserEntity staff) {
    this.username = staff.getUsername();
    this.password = staff.getPassword();
    this.active = staff.isActive();
    this.authorities =
      Arrays
        .stream(staff.getRoles().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
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
    return active;
  }
}
