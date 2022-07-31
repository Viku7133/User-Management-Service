package com.example.userService.repository;

import com.example.userService.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Integer> {

  Optional<UserEntity> findByUserId(Integer userId);

  Optional<UserEntity> findByEmailId(String emailId);

}
