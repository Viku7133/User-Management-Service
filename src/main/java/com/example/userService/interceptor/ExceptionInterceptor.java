package com.example.userService.interceptor;

import com.example.userService.constants.ErrorMessage;
import com.example.userService.exception.CustomException;
import com.example.userService.response.BaseErrorResponse;
import com.example.userService.util.LoggerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor {

  @ExceptionHandler(CustomException.class)
  public final ResponseEntity<BaseErrorResponse> handleCustomException(CustomException ex) {
    LoggerUtil.error("ERROR : " + ex.getMessage());
    return new ResponseEntity<>(new BaseErrorResponse(false, ex.errorMessage),
        HttpStatus.valueOf(ex.errorCode));
  }

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<BaseErrorResponse> handleAllException(Exception ex) {
    LoggerUtil.error("ERROR : " + ex.getMessage());
    return new ResponseEntity<>(new BaseErrorResponse(false, ErrorMessage.INTERNAL_SERVER_ERROR),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
