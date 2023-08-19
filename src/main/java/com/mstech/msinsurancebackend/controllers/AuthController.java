package com.mstech.msinsurancebackend.controllers;

import com.mstech.msinsurancebackend.models.LoginRequest;
import com.mstech.msinsurancebackend.models.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  @PostMapping("/auth/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request) {
    System.out.println(
      "Email: " + request.getEmail() + "\nPassword: " + request.getPassword()
    );
    return null;
  }

  @PostMapping("auth/register")
  public void register(@RequestBody @Validated LoginRequest request) {
    System.out.println("Need to configure this.");
  }
}
