package com.mstech.msinsurancebackend.controllers;

import com.mstech.msinsurancebackend.dtos.CategoryDTO;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

  @PostMapping(value = "/category")
  public void addCategory(@ModelAttribute CategoryDTO cat) {
    String fileName = cat.getCatImage().getOriginalFilename();
    System.out.println("Category Configuration...");
    System.out.println(
      "Name: " +
      cat.getCatName() +
      "\nImageName: " +
      fileName +
      "\nDesc: " +
      cat.getCatDescription() +
      "\nAvailability: " +
      cat.isAvailability() +
      "\nFeatured? " +
      cat.isFeatured() +
      "\n Created Date: " +
      cat.getCreatedDate() +
      "\n Updated Date: " +
      cat.getUpdatedDate()
    );
  }
}
