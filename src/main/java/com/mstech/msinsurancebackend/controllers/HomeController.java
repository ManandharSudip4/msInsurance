package com.mstech.msinsurancebackend.controllers;

import com.mstech.msinsurancebackend.security.jwt.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

  private final JwtProperties jwtProperties;

  @GetMapping("/home")
  public String home() {
    System.out.println("My Secret Key: " + jwtProperties.getSecretKey());
    return ("<h1>MS Insurance Pvt. Ltd.</h1>\n<h2>Bhaktapur, Nepal</h2>");
  }
}
