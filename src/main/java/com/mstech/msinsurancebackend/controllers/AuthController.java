package com.mstech.msinsurancebackend.controllers;

import com.mstech.msinsurancebackend.models.LoginRequest;
import com.mstech.msinsurancebackend.models.LoginResponse;
import com.mstech.msinsurancebackend.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  public final AuthService authService;

  @PostMapping("/auth/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request) {
    return authService.attemptLogin(request.getEmail(), request.getPassword());
  }

  @PostMapping("auth/register")
  public void register(@RequestBody @Validated LoginRequest request) {
    System.out.println("Need to configure this.");
  }
}
