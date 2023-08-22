package com.mstech.msinsurancebackend.dtos;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CategoryDTO {

  private Long id;
  private String catName;
  private String catDescription;
  private MultipartFile catImage;
  private boolean availability;
  private boolean isFeatured;
  private Date createdDate = new Date();
  private Date updatedDate = new Date();
}
