package com.example.userService.manager;

import com.example.userService.entity.UserEntity;
import com.example.userService.repository.UserRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class UserManager {

  @Autowired
  private UserRepo userRepository;

  /**
   * @param userEntity
   * @return Optional(UserEntity)
   */
  public Optional<UserEntity> save(UserEntity userEntity) {
    if (ObjectUtils.isEmpty(userEntity)) {
      return Optional.empty();
    }
    return Optional.of(userRepository.save(userEntity));
  }


  /**
   * @param userId
   * @return Optional(UserEntity)
   */
  public Optional<UserEntity> getUserEntity(Integer userId) {
    if (ObjectUtils.isEmpty(userId)) {
      return Optional.empty();
    }
    return userRepository.findByUserId(userId);
  }

  public Optional<UserEntity> getUserEntityByEmailId(String email) {
    if (ObjectUtils.isEmpty(email)) {
      return Optional.empty();
    }
    Optional<UserEntity> userEntity=  userRepository.findByEmailId(email);
    if(userEntity.isPresent()){
      System.out.println(userEntity.get().toString());
    }
    return userEntity;
  }

}
