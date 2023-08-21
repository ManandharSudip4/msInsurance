package com.mstech.msinsurancebackend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mstech.msinsurancebackend.entities.UserEntity;
import com.mstech.msinsurancebackend.services.UserService;

@RestController
@RequestMapping(path = "api/v1")
public class UserController {

    private final UserService staffService;


    public UserController(UserService staffService) {
        this.staffService = staffService;
    }


    @GetMapping("/staff")
    public List<UserEntity> getStaffs(){
        return staffService.getStaffs();
    }

    @PostMapping("/staff")
    public void registerNewStaff(@RequestBody UserEntity staff){
        System.out.println(staff.getUsername() + " is added to database as " + staff.getRoles());
        staffService.registerNewStaff(staff);
    }
}
