package com.mstech.msinsurancebackend.controllers;

import com.mstech.msinsurancebackend.security.UserPrincipal;
import com.mstech.msinsurancebackend.security.jwt.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

  private final String greetings =
    "<h1>MS Insurance Pvt. Ltd.</h1>\n<h2>Bhaktapur, Nepal</h2>";

  @GetMapping("/home")
  public String home(@AuthenticationPrincipal UserPrincipal principal) {
    return (
      "Hi, " +
      principal.getAuthorities() +
      " User: " +
      principal.getUsername() +
      " " +
      greetings
    );
  }

  @GetMapping("/staff")
  public String user(@AuthenticationPrincipal UserPrincipal principal) {
    return (
      "Hi, " +
      principal.getAuthorities() +
      " User: " +
      principal.getUsername() +
      " " +
      greetings
    );
  }

  @GetMapping("/admin")
  public String admin(@AuthenticationPrincipal UserPrincipal principal) {
    return (
      "Hi, " +
      principal.getAuthorities() +
      " ADMIN User: " +
      principal.getUsername() +
      " " +
      greetings
    );
  }
}
