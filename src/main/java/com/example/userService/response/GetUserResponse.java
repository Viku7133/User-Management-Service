package com.example.userService.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserResponse {

  @JsonProperty("name")
  private String userName;

  @JsonProperty("email")
  private String emailId;

  @JsonProperty("addressPincode")
  private String pinCode;

  @JsonProperty("stateName")
  private String state;

}
