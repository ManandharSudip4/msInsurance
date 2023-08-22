package com.mstech.msinsurancebackend.controllers;

import com.mstech.msinsurancebackend.dtos.CategoryDTO;
import com.mstech.msinsurancebackend.entities.CategoryEntity;
import com.mstech.msinsurancebackend.services.CategoryService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

  // @Autowired
  private final CategoryService categoryService;

  @PostMapping(value = "/category")
  public ResponseEntity<CategoryEntity> addCategory(
    @ModelAttribute CategoryDTO cat
  ) throws IOException {
    return categoryService.addNewCategory(cat);
  }
}
