package com.mstech.msinsurancebackend.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipalAuthenticationToken
  extends AbstractAuthenticationToken {

  private final UserPrincipal userPrincipal;

  public UserPrincipalAuthenticationToken(UserPrincipal principal) {
    super(principal.getAuthorities());
    this.userPrincipal = principal;
    setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    throw new UnsupportedOperationException(
      "Unimplemented method 'getCredentials'"
    );
  }

  @Override
  public UserPrincipal getPrincipal() {
    return userPrincipal;
  }
}
