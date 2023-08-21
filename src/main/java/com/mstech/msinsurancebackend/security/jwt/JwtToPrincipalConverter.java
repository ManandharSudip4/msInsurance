package com.mstech.msinsurancebackend.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.mstech.msinsurancebackend.security.UserPrincipal;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtToPrincipalConverter {

  public UserPrincipal convert(DecodedJWT jwt) {
    return UserPrincipal
      .builder()
      .userId(Long.valueOf(jwt.getSubject()))
      .email(jwt.getClaim("e").asString())
      .authorities(extractAuthorityFromClaim(jwt))
      .build();
  }

  private List<SimpleGrantedAuthority> extractAuthorityFromClaim(
    DecodedJWT jwt
  ) {
    var claim = jwt.getClaim("a");
    if (claim.isNull() || claim.isMissing()) return List.of();
    return claim.asList(SimpleGrantedAuthority.class);
  }
}
