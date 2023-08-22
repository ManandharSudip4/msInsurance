package com.mstech.msinsurancebackend.services;

import com.mstech.msinsurancebackend.dtos.CategoryDTO;
import com.mstech.msinsurancebackend.entities.CategoryEntity;
import com.mstech.msinsurancebackend.repositories.CategoryRepository;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  public ResponseEntity<CategoryEntity> addNewCategory(CategoryDTO requestData)
    throws IOException {
    String uploadDir =
      "public/images/CategoryImage/" + requestData.getCatName();
    MultipartFile imageFile = requestData.getCatImage();
    String filename = uploadFunction(uploadDir, imageFile);

    CategoryEntity categoryEntity = new CategoryEntity();

    if (filename != null && !filename.isEmpty()) {
      categoryEntity.setCatImage(filename);
    }

    categoryEntity.setCatName(requestData.getCatName());
    categoryEntity.setCatDescription(requestData.getCatDescription());
    categoryEntity.setAvailability(requestData.isAvailability());
    categoryEntity.setFeatured(requestData.isFeatured());
    categoryEntity.setCreatedDate(requestData.getCreatedDate());
    categoryEntity.setUpdatedDate(requestData.getUpdatedDate());

    System.out.println("Category Configuration...");
    System.out.println("\nSucceed: " + filename);
    return new ResponseEntity<>(
      categoryRepository.save(categoryEntity),
      HttpStatus.CREATED
    );
  }

  private String uploadFunction(String uploadDir, MultipartFile catImage)
    throws IOException {
    String filename = catImage.getOriginalFilename();
    Path uploadPath = Paths.get(uploadDir);

    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }

    try (InputStream inputStream = catImage.getInputStream()) {
      Path filePath = uploadPath.resolve(filename);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
      return filename;
    } catch (IOException ioe) {
      throw new IOException("Could not sabe image file: " + filename, ioe);
    }
  }
}
