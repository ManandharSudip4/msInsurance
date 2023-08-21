package com.mstech.msinsurancebackend.services;

import com.mstech.msinsurancebackend.models.LoginResponse;
import com.mstech.msinsurancebackend.security.UserPrincipal;
import com.mstech.msinsurancebackend.security.jwt.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final JwtIssuer jwtIssuer;
  private final AuthenticationManager authenticationManager;

  public LoginResponse attemptLogin(String email, String password) {
    var authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(email, password)
    );
    // performs an actual authentication process.
    // It checks the provided credentials against the user information stored in
    // our application's user repository (such as a database).
    // If the credentials match a user record, the authentication process succeeds,
    // and an Authentication object is returned.

    SecurityContextHolder.getContext().setAuthentication(authentication);

    // If the authentication process is successful (i.e., the user's credentials are valid),
    // the returned Authentication object represents the authenticated user.
    // This Authentication object is then set in the SecurityContextHolder.
    // The SecurityContextHolder is a thread-local holder for the security context,
    // which includes the authenticated user's details.

    var principal = (UserPrincipal) authentication.getPrincipal();

    var roles = principal
      .getAuthorities()
      .stream()
      .map(GrantedAuthority::getAuthority)
      .toList();

    try {
      var token = jwtIssuer.issue(
        principal.getUserId(),
        principal.getEmail(),
        roles
      );
      return LoginResponse.builder().accessToken(token).build();
    } catch (Exception e) {
      return LoginResponse.builder().accessToken(e.getMessage()).build();
    }
  }
}
