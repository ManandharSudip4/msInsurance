package com.mstech.msinsurancebackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mstech.msinsurancebackend.models.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long>{
    Optional<Staff> findByUsername(String username);
}
