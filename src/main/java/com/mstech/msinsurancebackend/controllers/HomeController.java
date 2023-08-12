package com.mstech.msinsurancebackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return ("<h1>MS Insurance Pvt. Ltd.</h1>\n<h2>Bhaktapur, Nepal</h2>");
    }
}
