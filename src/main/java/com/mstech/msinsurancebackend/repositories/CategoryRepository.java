package com.mstech.msinsurancebackend.repositories;

import com.mstech.msinsurancebackend.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
  extends JpaRepository<CategoryEntity, Long> {}
