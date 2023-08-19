package com.mstech.msinsurancebackend.controllers;

import com.mstech.msinsurancebackend.exception.ResourceNotFoundException;
import com.mstech.msinsurancebackend.models.ErrorMessage;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorMessage> resourceNotFoundException(
    ResourceNotFoundException ex,
    WebRequest request
  ) {
    ErrorMessage message = new ErrorMessage(
      HttpStatus.NOT_FOUND.value(),
      new Date(),
      ex.getMessage(),
      request.getDescription(false)
    );

    return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
  }
}
