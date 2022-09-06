package com.example.registerservice.exception.handler;

import com.example.registerservice.exception.OwnerNotFoundException;
import com.example.registerservice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RegisterServiceExceptionHandler {


  @ExceptionHandler(value = OwnerNotFoundException.class)
  public ResponseEntity<Object> ownerNotFoundException(OwnerNotFoundException exception) {
    return new ResponseEntity<>("Owner not found", HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = UserNotFoundException.class)
  public ResponseEntity<Object> userNotFoundException(UserNotFoundException exception) {
    return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
  }

}
