package com.mstech.msinsurancebackend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mstech.msinsurancebackend.models.Staff;
import com.mstech.msinsurancebackend.services.StaffService;

@RestController
@RequestMapping(path = "api/v1")
public class StaffController {

    private final StaffService staffService;


    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }


    @GetMapping("/staff")
    public List<Staff> getStaffs(){
        return staffService.getStaffs();
    }

    @PostMapping("/staff")
    public void registerNewStaff(@RequestBody Staff staff){
        System.out.println(staff.getUsername() + " is added to database as " + staff.getRoles());
        staffService.registerNewStaff(staff);
    }
}
