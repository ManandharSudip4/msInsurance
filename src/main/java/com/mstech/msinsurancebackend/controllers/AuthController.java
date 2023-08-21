package com.mstech.msinsurancebackend.controllers;

import com.mstech.msinsurancebackend.entities.UserEntity;
import com.mstech.msinsurancebackend.models.LoginRequest;
import com.mstech.msinsurancebackend.models.LoginResponse;
import com.mstech.msinsurancebackend.services.AuthService;
import com.mstech.msinsurancebackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  private final UserService userService;

  @PostMapping("/auth/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request) {
    return authService.attemptLogin(request.getEmail(), request.getPassword());
  }

  @PostMapping("auth/register")
  public ResponseEntity<HttpStatus> register(
    @RequestBody @Validated UserEntity request
  ) {
    System.out.println("Configuring User Registration");
    return userService.registerNewStaff(request);
  }
}
