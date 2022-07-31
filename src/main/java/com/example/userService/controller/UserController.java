package com.example.userService.controller;

import com.example.userService.entity.UserEntity;
import com.example.userService.service.UserService;
import com.example.userService.exception.CustomException;
import com.example.userService.response.CreateUserResponse;
import com.example.userService.response.GetUserResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(value = "/create")
  public CreateUserResponse createUser(@RequestParam("name") String userName,
      @RequestParam("email") String email,
      @RequestParam("pin-code") String pinCode) throws CustomException {
    UserEntity userEntity = userService.createUserProfile(userName, email, pinCode);
    return CreateUserResponse
        .builder()
        .userId(Integer.toString(userEntity.getUserId()))
        .build();
  }

  @GetMapping(value = "/{userId}")
  public GetUserResponse getUser(@NonNull @PathVariable Integer userId) throws CustomException {
    UserEntity userEntity = userService.getUserProfile(userId);
    return GetUserResponse.builder()
        .userName(userEntity.getName())
        .emailId(userEntity.getEmailId())
        .pinCode(userEntity.getPinCode())
        .state(userEntity.getState())
        .build();
  }


}
