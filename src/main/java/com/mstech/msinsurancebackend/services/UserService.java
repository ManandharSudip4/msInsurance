package com.mstech.msinsurancebackend.services;

import com.mstech.msinsurancebackend.entities.UserEntity;
import com.mstech.msinsurancebackend.exception.ResourceNotFoundException;
import com.mstech.msinsurancebackend.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.sound.midi.Soundbank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public List<UserEntity> getStaffs() {
    return userRepository.findAll();
  }

  public ResponseEntity<HttpStatus> registerNewStaff(UserEntity user) {
    Optional<UserEntity> oldUser = userRepository.findByUsername(
      user.getUsername()
    );

    if (oldUser.isPresent()) {
      throw new ResourceNotFoundException("Username already Taken");
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
