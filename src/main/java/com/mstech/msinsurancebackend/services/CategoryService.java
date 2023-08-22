package com.mstech.msinsurancebackend.services;

import com.mstech.msinsurancebackend.dtos.CategoryDTO;
import com.mstech.msinsurancebackend.entities.CategoryEntity;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
  public ResponseEntity<CategoryEntity> addNewCategory(CategoryDTO requestData)
    throws IOException;
}
