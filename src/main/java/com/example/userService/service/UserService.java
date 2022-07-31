package com.example.userService.service;

import com.example.userService.entity.UserEntity;
import com.example.userService.constants.ErrorMessage;
import com.example.userService.exception.CustomException;
import com.example.userService.external.service.PostOfficeService;
import com.example.userService.manager.UserManager;
import com.example.userService.util.LoggerUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class UserService {

  @Autowired
  private UserManager userManager;

  @Autowired
  private PostOfficeService postOfficeService;

  /**
   * @param name
   * @param email
   * @param pinCode
   * @return
   * @throws CustomException
   */
  public UserEntity createUserProfile(String name, String email, String pinCode)
      throws CustomException {
    validateParams(name, email, pinCode);
    String state = postOfficeService.getState(pinCode);
    if (ObjectUtils.isEmpty(state)) {
      throw new CustomException(ErrorMessage.INVALID_PIN_CODE, HttpStatus.NOT_FOUND.value());
    }
    UserEntity userEntity = UserEntity.builder()
        .name(name)
        .emailId(email)
        .pinCode(pinCode)
        .state(state)
        .build();
    Optional<UserEntity> userEntityOptional = userManager.save(userEntity);
    if (!userEntityOptional.isPresent()) {
      LoggerUtil.error("");
      throw new CustomException(ErrorMessage.INTERNAL_SERVER_ERROR,
          HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    return userEntityOptional.get();
  }

  /**
   * @param userId
   * @return
   * @throws CustomException
   */
  public UserEntity getUserProfile(Integer userId) throws CustomException {
    Optional<UserEntity> userEntityOptional = userManager.getUserEntity(userId);
    if (!userEntityOptional.isPresent()) {
      LoggerUtil.error("User is not present.");
      throw new CustomException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.value());
    }
    return userEntityOptional.get();
  }

  /**
   * @param userName
   * @param email
   * @param pinCode
   * @throws CustomException
   */
  private void validateParams(String userName, String email, String pinCode)
      throws CustomException {
    if (ObjectUtils.isEmpty(userName)) {
      LoggerUtil.info("user name is required.");
      throw new CustomException(ErrorMessage.EMPTY_USER_NAME, HttpStatus.BAD_REQUEST.value());
    }
    if (ObjectUtils.isEmpty(email)) {
      LoggerUtil.info("email-id is required.");
      throw new CustomException(ErrorMessage.EMPTY_EMAIL, HttpStatus.BAD_REQUEST.value());
    }
    if (ObjectUtils.isEmpty(pinCode)) {
      LoggerUtil.info("pin-code is required");
      throw new CustomException(ErrorMessage.EMPTY_PIN_CODE, HttpStatus.BAD_REQUEST.value());
    }
    Optional<UserEntity> userEntityOptional = userManager.getUserEntityByEmailId(email);
    if (userEntityOptional.isPresent()) {
      LoggerUtil.info("user is already present");
      throw new CustomException(ErrorMessage.DUPLICATE_EMAIL, HttpStatus.BAD_REQUEST.value());
    }
  }

}