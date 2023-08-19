package com.mstech.msinsurancebackend.services;

import com.mstech.msinsurancebackend.models.Staff;
import com.mstech.msinsurancebackend.repositories.StaffRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

  private final StaffRepository staffRepository;

  public StaffService(StaffRepository staffRepository) {
    this.staffRepository = staffRepository;
  }

  public List<Staff> getStaffs() {
    return staffRepository.findAll();
  }

  public void registerNewStaff(Staff staff) {
    Optional<Staff> oldStaff = staffRepository.findByUsername(
      staff.getUsername()
    );

    if (oldStaff.isPresent()) {
      throw new IllegalStateException("Username already Taken");
    }
    staffRepository.save(staff);
  }
}
