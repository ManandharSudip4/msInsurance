package com.mstech.msinsurancebackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "staff")
public class UserEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String username;

  private String firstName;
  private String lastName;

  @Column(nullable = false)
  private String password;

  private String roles;
  private boolean active;
  private Date createdAt;
  private Date updatedAt;
}
