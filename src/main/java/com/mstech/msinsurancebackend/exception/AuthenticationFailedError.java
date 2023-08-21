package com.mstech.msinsurancebackend.exception;

public class AuthenticationFailedError extends RuntimeException {

  public AuthenticationFailedError(String msg) {
    super(msg);
  }
}
