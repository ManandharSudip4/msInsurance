package com.mstech.msinsurancebackend.controllers;

import com.mstech.msinsurancebackend.exception.ResourceNotFoundException;
import com.mstech.msinsurancebackend.models.LoginRequest;
import com.mstech.msinsurancebackend.models.LoginResponse;
import com.mstech.msinsurancebackend.security.jwt.JwtIssuer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final JwtIssuer jwtIssuer;

  @PostMapping("/auth/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request) {
    System.out.println(
      "Email: " + request.getEmail() + "\nPassword: " + request.getPassword()
    );
    try {
      var token = jwtIssuer.issue(1L, request.getEmail(), List.of("ADMIN"));
      return LoginResponse.builder().accessToken(token).build();
    } catch (Exception e) {
      return LoginResponse.builder().accessToken(e.getMessage()).build();
    }
  }

  @PostMapping("auth/register")
  public void register(@RequestBody @Validated LoginRequest request) {
    System.out.println("Need to configure this.");
  }
}
