package com.example.userService.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "user_entity")
@Table(name = "user_entity")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer userId;

  @Column(name = "name")
  private String name;

  @Column(name = "email_id")
  private String emailId;

  @Column(name = "pin_code")
  private String pinCode;

  @Column(name = "state")
  private String state;

}
