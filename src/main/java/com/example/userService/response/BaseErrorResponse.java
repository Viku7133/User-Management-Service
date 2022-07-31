package com.example.userService.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseErrorResponse {

  @JsonProperty("success")
  private Boolean success = Boolean.FALSE;

  @JsonProperty("error")
  private String errorMessage;

}