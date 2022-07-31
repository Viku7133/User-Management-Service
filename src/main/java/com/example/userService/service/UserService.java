package com.example.userService.service;

import com.example.userService.entity.UserEntity;
import com.example.userService.constants.ErrorMessage;
import com.example.userService.exception.CustomException;
import com.example.userService.external.service.PostOfficeService;
import com.example.userService.manager.UserManager;
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
   *
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
      throw new CustomException(ErrorMessage.INTERNAL_SERVER_ERROR,
          HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    return userEntityOptional.get();
  }

  /**
   *
   * @param userId
   * @return
   * @throws CustomException
   */
  public UserEntity getUserProfile(Integer userId) throws CustomException {
    Optional<UserEntity> userEntityOptional = userManager.getUserEntity(userId);
    if (!userEntityOptional.isPresent()) {
      throw new CustomException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.value());
    }
    return userEntityOptional.get();
  }

  /**
   *
   * @param userName
   * @param email
   * @param pinCode
   * @throws CustomException
   */
  private void validateParams(String userName, String email, String pinCode)
      throws CustomException {
    if (ObjectUtils.isEmpty(userName)) {
      throw new CustomException(ErrorMessage.EMPTY_USER_NAME, HttpStatus.BAD_REQUEST.value());
    }
    if (ObjectUtils.isEmpty(email)) {
      throw new CustomException(ErrorMessage.EMPTY_EMAIL, HttpStatus.BAD_REQUEST.value());
    }
    if (ObjectUtils.isEmpty(pinCode)) {
      throw new CustomException(ErrorMessage.EMPTY_PIN_CODE, HttpStatus.BAD_REQUEST.value());
    }
    Optional<UserEntity> userEntityOptional = userManager.getUserEntityByEmailId(email);
    if (userEntityOptional.isPresent()) {
      throw new CustomException(ErrorMessage.DUPLICATE_EMAIL, HttpStatus.BAD_REQUEST.value());
    }
  }

}