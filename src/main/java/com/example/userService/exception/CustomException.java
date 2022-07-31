package com.example.userService.exception;

public class CustomException extends Exception {

  public String errorMessage;

  public Integer errorCode;

  public CustomException(String errorMessage, Integer errorCode) {
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
  }

}