package com.mstech.msinsurancebackend.services;

import com.mstech.msinsurancebackend.entities.UserEntity;
import com.mstech.msinsurancebackend.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository staffRepository;

  public UserService(UserRepository staffRepository) {
    this.staffRepository = staffRepository;
  }

  public List<UserEntity> getStaffs() {
    return staffRepository.findAll();
  }

  public void registerNewStaff(UserEntity staff) {
    Optional<UserEntity> oldStaff = staffRepository.findByUsername(
      staff.getUsername()
    );

    if (oldStaff.isPresent()) {
      throw new IllegalStateException("Username already Taken");
    }
    staffRepository.save(staff);
  }
}
