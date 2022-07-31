package com.example.userService.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerUtil {

  private static final Logger log = LoggerFactory.getLogger("User-Service");

  public static void info(String message){
    log.info(message);
  }

  public static void error(String message){
    log.error(message);
  }


}
